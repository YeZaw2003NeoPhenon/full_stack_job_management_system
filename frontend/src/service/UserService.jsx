
const BaseURL = 'http://localhost:8080/api/v1/users'

const createUser = async(newUser) => {
    const url = `${BaseURL}/create`
    await fetch(url, {
        method : 'POST',
        headers: {
          'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newUser)
        })
}

export {createUser}