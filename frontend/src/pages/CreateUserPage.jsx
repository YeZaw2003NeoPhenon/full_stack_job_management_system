import React, { useState } from 'react'
import { createUser } from '../service/UserService'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'

const CreateUserPage = () => {

   const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [role, setRole] = useState('USER')

  const navigate = useNavigate()

   const handleSubmit = (e) => {
    e.preventDefault()

    const newUser = {
      username,
      email,
      password,
      role
    }
     const jsonData = createUser(newUser)
      console.log(jsonData);
      toast('User created successfully!')
      navigate('/jobs')
  
  }
 return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded shadow">
      <h2 className="text-2xl font-bold mb-6 text-center">Create New User</h2>
      <form onSubmit={handleSubmit} className="space-y-4">

        <div>
          <label className="block mb-1 font-medium" htmlFor="username">Username</label>
          <input
            id="username"
            type="text"
            required
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            className="w-full px-3 py-2 border rounded"
            placeholder="Username"
          />
        </div>

        <div>
          <label className="block mb-1 font-medium" htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full px-3 py-2 border rounded"
            placeholder="user@example.com"
          />
        </div>

        <div>
          <label className="block mb-1 font-medium" htmlFor="password">Password</label>
          <input
            id="password"
            type="password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full px-3 py-2 border rounded"
            placeholder="Password"
          />
        </div>

        <div>
          <label className="block mb-1 font-medium" htmlFor="role">Role</label>
          <select
            id="role"
            value={role}
            onChange={(e) => setRole(e.target.value)}
            className="w-full px-3 py-2 border rounded"
            required
          >
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
            {/* add more roles here if you have */}
          </select>
        </div>

        <button
          type="submit"
          className="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-3 rounded font-semibold"
        >
          Create User
        </button>
      </form>
    </div>
  )
}

export default CreateUserPage