
import MainLayout from "./layouts/MainLayout";
import HomePage from "./pages/HomePage";
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from "react-router-dom";
import { NotFound } from "./pages/NotFound";
import JobsPage from "./pages/JobsPage";
import { jobLoader} from "./pages/JobPage";
import JobPage from "./pages/JobPage";
import AddJobPage from "./pages/AddJobPage";
import UpdateJobPage from "./pages/UpdateJobPage";
import { createJob,deleteJobById , updateJob} from "./service/JobService";
import Spinner from "./components/Spinner";
import { useEffect, useState } from "react";
import LoginPage from "./security/LoginPage";
import { authMeUser } from "./service/authService";
import CreateUserPage from "./pages/CreateUserPage";
import Navbar from "./components/Navbar";
const App = () => {

  const[user, setUser] = useState(null)
  const[checkingAuth, setCheckingAuth] = useState(true)
  const[isLogin , setIsLogin] = useState(false)

  useEffect(() => {
      const fetchUser = async() => {
       try{
        const data = await authMeUser()
        setUser(data)
        setIsLogin(true)
       }
       catch(error){
        console.log(`Error while fetching user ${error}`);
        setIsLogin(false)
       }
       finally{
        setCheckingAuth(false)
       }
      }
      fetchUser()
  },[])

  const addJobForm = async (newJob) => {
    createJob(newJob)
    return;
  }

  const deleteJob = async (id) => {
        deleteJobById(id)
    return;
  }

  const UpdateJob = async(newJob,id) => {
     updateJob(newJob,id)
     return;
  }

  const RequiredAuth = ({children}) => {
    if(!user && !isLogin){
      return <LoginPage onLogin = {(loggedUser) => setUser(loggedUser)} />
    }
    return children; // if already logged in , display every inner components
  }

   if(checkingAuth){
    return <Spinner isLoading = {checkingAuth}/>
   }

  const router = createBrowserRouter(
      createRoutesFromElements(
      <Route path='/' element = {<MainLayout/>}>
        <Route index element = {<RequiredAuth><HomePage user = {user} setUser={setUser}/></RequiredAuth>}/>
        <Route path = '/jobs' element = {<RequiredAuth><JobsPage/></RequiredAuth>}/>
        <Route path = '/jobs/:id' element = {<RequiredAuth><JobPage deleteJob = {deleteJob}/></RequiredAuth>} loader = {jobLoader}/>
        <Route path = '/add-job' element = {<RequiredAuth><AddJobPage  addJobForm = {addJobForm}/></RequiredAuth>}/>
        <Route path = '/edit-job/:id' element = {<RequiredAuth><UpdateJobPage UpdateJob={UpdateJob}/></RequiredAuth>} loader = {jobLoader}/>
        <Route path = '*' element = {<NotFound/>}/>
        <Route path = '/user-create' element = {<CreateUserPage/>}></Route>
      </Route>
    )
  )

  return (
        <RouterProvider router={router}/>

  )
};

export default App