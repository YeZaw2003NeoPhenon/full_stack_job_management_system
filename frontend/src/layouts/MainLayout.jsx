import { Navbar } from '../components/Navbar'
import { Outlet } from 'react-router-dom'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
const MainLayout = ({user,setUser}) => {
  return (
    <div>
        <Navbar user={user} setUser={setUser}/>
        <Outlet/>
        <ToastContainer className="mt-2"/>
    </div>
  )
}

export default MainLayout