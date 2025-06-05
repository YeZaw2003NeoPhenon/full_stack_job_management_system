
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

const App = () => {
  
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

  const router = createBrowserRouter(
      createRoutesFromElements(
      <Route path='/' element = {<MainLayout/>}>
        <Route index element={<HomePage/>}/>
        <Route path = '/jobs' element = {<JobsPage/>}/>
        <Route path = '/jobs/:id' element = {<JobPage deleteJob = {deleteJob}/>} loader = {jobLoader}/>
        <Route path = '/add-job' element = {<AddJobPage  addJobForm = {addJobForm}/>}/>
        <Route path = '/edit-job/:id' element = {<UpdateJobPage UpdateJob={UpdateJob}/>} loader = {jobLoader}/>
        <Route path = '*' element = {<NotFound/>}/>
      </Route>
    )
  )

  return (
        <RouterProvider router={router}/>

  )
};

export default App