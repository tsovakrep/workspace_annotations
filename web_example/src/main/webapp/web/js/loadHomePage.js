function resolveAfter2Seconds() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve('resolved');
    }, 2000);
  });
}

function asyncCall() {
  console.log('calling');
  var result = resolveAfter2Seconds();
  console.log(result);
  // expected output: "resolved"
}

asyncCall();