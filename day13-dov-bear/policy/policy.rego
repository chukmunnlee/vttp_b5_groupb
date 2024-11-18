package main

import future.keywords.in
import future.keywords.if

allow_list := { "npm ci", "npm ci --omit=dev", "apt update", "apt upgrade -y" }
trusted_repo := [ "docker.io/library/", "library/", "ghcr.io/chukmunnlee/" ]

# Cannot use :latest tag 
deny_image_latest_tag[msg] {
	some i
		input[i].Cmd = "from"
		img := split(input[i].Value[0], ":")
		count(img) > 1
		img[1] == "latest" 
		msg := sprintf("Cannot use 'latest' in image: %s", [ input[i].Value[0] ])
}

# Must specify image tag 
deny_image_no_tag[msg] {
	some i
		input[i].Cmd = "from"
		img := split(input[i].Value[0], ":")
		count(img) = 1
		msg := sprintf("Must use RepoDigest (@sha256:) instead of image tag: %s", [ input[i].Value[0] ])
}

# Must use repository digest to identify image
deny_image_non_repodigest_tag[msg] {
	some i
		input[i].Cmd = "from"
		img := split(input[i].Value[0], "@")
		count(img) = 1
		msg := sprintf("Must use RepoDigest (@sha256:) to identify image: %s", [ input[i].Value[0] ])
}

# Ignore the tag rules if the image is from a trusted repository
exception[rules] {
	some i
		input[i].Cmd = "from"
		trusted = [ 1 | startswith(input[i].Value[0],  trusted_repo[_]) ]
		count(trusted) > 0
		rules := [ "image_latest_tag", "image_no_tag", "image_non_repodigest_tag" ]
}

# Can only run commands in allow_list 
deny[msg] {
	some i
		input[i].Cmd = "run"
		cmd := regex.split("\\&\\& | \\|\\|", input[i].Value[0])
		s_cmd = { trim_space(c) | c := cmd[_] }
		count(s_cmd - allow_list) > 0
		msg := sprintf("Cannot RUN the following: %s. You can only RUN these commands: %s", [ cmd, allow_list ])
}

# Must not run as root
deny[msg] {
	user := [ user | input[i].Cmd == "user"; user = input[i].Value[0] ]
	count(user) <= 0
	msg := sprintf("Cannot run as root", [])
}

deny[msg] {
	user := [ to_number(user) | input[i].Cmd == "user"; user = input[i].Value[0] ]
	user[0] < 1000
	msg := sprintf("Cannot run as root: %s", [user])
}

did_not_include_directive(directive) = true if count([ 1 | input[i].Cmd = directive ]) <= 0

# Label the image
warn[msg] {
	did_not_include_directive("label")
	msg := sprintf("LABEL the image", [])
}

# Include MAINTAINER
warn[msg] {
	did_not_include_directive("maintainer")
	msg := sprintf("Add MAINTAINER to image", [])
}

# Include HEALTHCHECK
warn[msg] {
	did_not_include_directive("healthcheck")
	msg := sprintf("Add HEALTHCHECK to the image", [])
}

# Use ENTRYPOINT to start the container
warn[msg] {
	input[i].Cmd == "cmd"
	did_not_include_directive("entrypoint")
	msg := sprintf("Prefer ENTRYPOINT over CMD: %s", [ input[i].Value ])
}
