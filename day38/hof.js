const add = (x, y) => {
  return x + y
}

const apply = (f, x, y) => {
  const ans = f(x, y)
  return ans
}

console.info('value of the funtion: add = ', add)
console.info('application of the function: add', add(3, 5))
console.info('apply add 5, 4', apply(add, 5, 4))

var ans = apply(add, apply(add, 3, 4), apply(add, 10, 12))
let a = apply(add, 3, 4)
let b = apply(add, 10, 12)
let c = apply(add, a, b)
console.info('ans = ', ans)
