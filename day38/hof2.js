const hello = (name) => {
  return `Hello ${name}`
}

var v = hello('Fred')
console.info(hello('fred'))

const mkHello = (name) => {
  return (n = 0) => {
    // name is a free variable
    let s = `Hello ${name}`
    for (let i = 0; i < n; i++)
      s += `| Hello ${name}`
    return s
  }
}

const greetFred = mkHello('Fred')
const greetBarney = mkHello('Barney')

console.info('fred: ', greetFred)
console.info('greet fred: ', greetFred())

console.info('barney: ', greetBarney)
console.info('greet barney: ', greetBarney(3))
