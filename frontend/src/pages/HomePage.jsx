import Hero from '../components/Hero'
import HomeCard from '../components/HomeCards'
import Joblistings from '../components/Joblistings'
import ViewAllJobs from '../components/ViewAllJobs'
import LogoutButton from '../security/LogoutButton'

 const HomePage = ({user, setUser}) => {
  return (
    <>
        <Hero title = "Become A Software Engineer" subtitle = "Find the tech related job that fits your skills and needs"/>
        <HomeCard/>
        <Joblistings isHome = {true}/>
        <ViewAllJobs/>
        <LogoutButton user = {user} setUser={setUser}/>
    </>
  )
}
export default HomePage