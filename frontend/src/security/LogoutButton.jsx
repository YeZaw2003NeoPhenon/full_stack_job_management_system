import React from 'react'
import { logout } from '../service/authService'
import { toast } from 'react-toastify'
const LogoutButton = ({setUser}) => {

    const handleLogout = async() => {
        try{
            await logout()
           setUser(null)
           toast('user logout successfully')
        }
        catch(error){
            console.log(error); 
        }
    }

return (
    <button
      onClick={handleLogout}
      className="bg-red-500 hover:bg-red-600 text-white font-semibold px-4 py-2 rounded-lg shadow-md transition"
    >
      Logout
    </button>
  );
}

export default LogoutButton