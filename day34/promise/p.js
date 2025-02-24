
let p = new Promise(
  // pass the promise a function
  (resolve, reject) => {
    // you perform your task
    // if the task is successful, call resolve(result)
    // if the task fails, call reject(reason)
    setTimeout(() => {
      console.info('COMPLETED!!!')
      //reject('task has failed')
      resolve('task is successful')
    }, 3000)
  }
)

p
.then(result => { 
  console.info('>>> promise resolved: ', result) 
  //return 'this is another result'
  throw 'this is an error from the second promise'
})
.then(result => {
  console.info('second promose: ', result)
  return 123
})
.catch( error => { 
  console.info('>>> promise has failed: ', error) 
  return 'resovled the error'
})
.then(result => {
  console.info('>> value = ', result)
})


console.info('>> p = ', p)

