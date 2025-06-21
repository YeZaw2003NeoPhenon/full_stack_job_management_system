import logo from '../assets/images/logo.png'
import { NavLink } from 'react-router-dom';
import SearchJob from './SearchJob';

export const Navbar = () => {
  const ActiveClass = ({isActive}) => isActive ? 'text-white bg-black hover:bg-gray-900 hover:text-white rounded-md px-3 py-2' : 
                            'text-white hover:bg-gray-900 hover:text-white rounded-md px-3 py-2'
    return (
        <>
    <nav className="bg-indigo-700 border-b border-indigo-500">
      <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
        <div className="flex h-20 items-center justify-between">
          <div
           className="flex flex-1 items-center justify-center md:items-stretch md:justify-start"
          >
            {/* <!-- Logo --> */}
            <NavLink className="flex flex-shrink-0 items-center mr-4" href="/index.html">
              <img
               className="h-10 w-auto"
                src={logo}
                alt="React Jobs"
              />
             <span className="hidden md:block text-white text-2xl font-bold ml-2">Bolstered By React</span>
            </NavLink>
             <SearchJob/>
            <div className="md:ml-auto">
              <div className="flex space-x-2">
               <NavLink to ="/" 
               className = {ActiveClass}>Home</NavLink>
                <NavLink
                  to ="/jobs"
                 className = {ActiveClass}
                  >Jobs</NavLink>
                <NavLink
                  to ="/add-job"
                 className={ActiveClass}>Add Job</NavLink>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
        </>
    )
}
export default Navbar;