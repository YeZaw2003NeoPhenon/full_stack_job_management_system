import Joblisting from './Joblisting'
import {useEffect,useState} from 'react'
import Spinner from './Spinner'
import { getAllJobs, getJobsByParam } from '../service/JobService'
import { useLocation } from 'react-router-dom'

const Joblisings = ({isHome = false}) => {
  
  const[jobs, setJobs] = useState([])
  const[isLoading, setIsLoading] = useState(true)

  const location = useLocation()

  const queryParam = new URLSearchParams(location.search).get("search");

   useEffect(() => {
    const fetchJobs = async () => {
      try{
        let jsonData;
        if(queryParam){
          jsonData = await getJobsByParam(queryParam)
        }
        else{
          jsonData = await getAllJobs(isHome ? 3 : null)
        }
       console.log('Fetched:', jsonData);
       console.log('message: ', jsonData.message)
       setJobs(jsonData.data)
      }
      catch(error){
        console.log(`fetching error ${error}`);
      }
      finally{
        setIsLoading(false)
      }
    }
    fetchJobs()
   },[])
    // const joblistings = isHome ? jobs.slice(0,3) : jobs
    
  return (
<section className="bg-blue-50 px-4 py-10">
      <div className="container-xl lg:container m-auto">
        <h2 className="text-3xl font-bold text-indigo-500 mb-6 text-center">
         {isHome ? 'Recent Jobs' : queryParam ? `Results for ${queryParam}` : 'Browse Jobs'}
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {
            isLoading ? (<Spinner isLoading = {isLoading}/>) :    
            (
              <>
            {
        jobs == null ? (
              'Not jobs in here'
            ) : (
           jobs.map((job) => {
          return(
            <Joblisting job = {job} key={job.id}/>
              )
            })
            )  
          }
              </>
            )
          }
        </div>
      </div>
    </section>
  )
}

export default Joblisings