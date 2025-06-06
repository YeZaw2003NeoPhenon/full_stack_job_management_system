
const BaseURL = 'http://localhost:8080/api/v1/auth'


const authMeUser = async() => {
        const res = await fetch('http://localhost:8080/api/v1/auth/me', {
                    credentials: 'include',
       })
         if (!res.ok) {
                throw new Error('Unauthorized');
         }
        const data = await res.json()
        return data;
}

const login = async (credentials) => {
     const res = await fetch('http://localhost:8080/api/v1/auth/login', {
            method : 'POST',
            headers: {
                 'Content-Type': 'application/x-www-form-urlencoded',
            },
              body: new URLSearchParams(credentials),
              credentials: 'include',
        })
        return res;
}

const logout = async () => {
        await fetch('http://localhost:8080/api/v1/auth/logout', {
                         method : 'POST', 
                         credentials: 'include',
                          headers: {
                            "Content-Type": "application/x-www-form-urlencoded",
             },
        })
}
export {authMeUser, login, logout}
