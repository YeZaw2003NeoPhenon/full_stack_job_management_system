
import React, { useState } from 'react'
import { toast } from 'react-toastify'
import { login, authMeUser} from '../service/authService'
import { useNavigate } from 'react-router-dom'
const LoginPage = ({onLogin}) => {

    const[credentials, setCredentials] = useState({email : "", password : ""})

    const[error , setError] = useState(null)

    const navigate = useNavigate()

    const handleLogin = async(e) => {
        e.preventDefault();

     try{
        const res = await login(credentials)
        if(res.ok){
            const user = await authMeUser()
            onLogin(user)
            toast("User successfully logged in")
        }
        else{
            setError('Invalid User Credentials!')
            toast.error('Log in fails!')
        }
     }
     catch(error){
        console.log(error);
        setError("Login failed");
        toast.error('Log in fails!')
     }
  }


  return (
<div className="flex items-center justify-center min-h-screen bg-blue-50 px-4">
  <div className="bg-white p-10 rounded-2xl shadow-2xl w-full max-w-xl">
    <h2 className="text-3xl font-bold text-center text-indigo-600 mb-8">Login to Your Account</h2>
    
    {error && (
      <div className="text-red-500 text-center mb-4 text-sm font-medium">
        {error}
      </div>
    )}
    
    <form onSubmit={handleLogin} className="space-y-6">
      <input
        type="text"
        placeholder="Email"
        value={credentials.email}
        onChange={(e) => setCredentials({ ...credentials, email: e.target.value })}
        className="w-full px-5 py-3 border border-gray-300 rounded-xl text-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
      />
      <input
        type="password"
        placeholder="Password"
        value={credentials.password}
        onChange={(e) => setCredentials({ ...credentials, password: e.target.value })}
        className="w-full px-5 py-3 border border-gray-300 rounded-xl text-lg focus:outline-none focus:ring-2 focus:ring-indigo-500"
      />
      <button
        type="submit"
        className="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 rounded-xl text-lg transition duration-300"
      >
        Login
      </button>

       <div className="flex justify-center">
    <button
      type="button"
      onClick={() => navigate('/user-create')}
      className="mt-4 text-indigo-600 hover:underline text-sm">
      Don’t have an account? Create one
    </button>
  </div>
    </form>
  </div>
</div>

  )
}

export default LoginPage