import React from 'react'
import { logout } from '../service/authService'
import { toast } from 'react-toastify'

import { useNavigate } from 'react-router-dom'
const LogoutButton = ({setUser}) => {

    const navigate = useNavigate()

    const handleLogout = async() => {
        try{
          await logout()
           setUser(null)
           toast('user logout successfully')
           navigate('/')
        }
        catch(error){
            console.log(error); 
        }
  }

return (
  <div className='flex justify-end mb-3 mr-4'>
    <button onClick={handleLogout}  className="bg-red-500 hover:bg-red-600 text-white font-semibold px-4 py-2 rounded-lg shadow-md transition"> 
      Logout
    </button>
  </div>
  );
}

export default LogoutButton