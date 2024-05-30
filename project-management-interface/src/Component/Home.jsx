import React, { useEffect,useState } from 'react'
import Navbar from './Navbar';
import Sidebar from './Sidebar';
import imageOne from "../assets/hero-image1.png"

function Home() {
    const [user, setuser] = useState();
    const [userProjects, setuserProjects] = useState([]);

    useEffect(() => {
        const systemUser = JSON.parse(sessionStorage.getItem("systemUser"));
        const projects = JSON.parse(sessionStorage.getItem("userProjects"));
        if (systemUser) {
            setuser(systemUser)
        }
        if (projects) {
            setuserProjects(projects)
        }

        return () => {
        }
        
    }, []);
    const loadProject =(project_id)=>{
    sessionStorage.setItem("projectId",project_id);
    window.location.href = "project";
  }

  return (
      <>
          <div className="page-row">
              <Sidebar/>
            <div className="project-wrapper">
                <Navbar />
                <div className="home-section">
                      <div className="container">
                          <div className="home-header">
                               <div className="home-image">
                                  <img src={imageOne} alt="" />
                              </div>
                              <div className="home-title">
                                  <h2>Hello, {user ? user.fullName:""}</h2>
                                  <p>Welcome to our Project Management System! We are excited to provide you with a robust and user-friendly platform to streamline your project planning, tracking, and collaboration.</p>
                              </div>
                          </div>
                          <div className="home-projects">
                              <h5><i className="lni lni-briefcase-alt"></i> Your Projects </h5>
                              <div className="home-project-card-container">
                                  {
                                      userProjects ? userProjects.map(project =>
                                        <div key={project.id} onClick={()=>loadProject(project.id)} className="home-project-cards">
                                              <h6><span></span> { project.title}</h6>
                                            <p>Project description</p>
                                        </div>
                                      ):""
                                  }
                                  <div className="home-project-cards project-card-add">
                                      <p>Lets add your project to our system</p>
                                      <a href='/createProject'>+ Add project</a>
                                  </div>
                              </div>
                              
                          </div>
                      </div>  
                </div>
            </div>
        </div>
      </>
  )
}

export default Home