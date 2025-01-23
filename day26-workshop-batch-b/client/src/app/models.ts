export interface Comment {
  user: string
  rating: number
  text: string
}

export interface Boardgame {
  gid: number
  name: string
  ranking: number
  url: string
  image: string
  comments: Comment[]
}
