import Hero from '../components/Hero'
import HomeCard from '../components/HomeCards'
import Joblistings from '../components/Joblistings'
import ViewAllJobs from '../components/ViewAllJobs'

 const HomePage = () => {
  return (
    <>
        <Hero title = "Become A Software Engineer" subtitle = "Find the tech related job that fits your skills and needs"/>
        <HomeCard/>
        <Joblistings isHome = {true}/>
        <ViewAllJobs/>
        
    </>
  )
}
export default HomePage