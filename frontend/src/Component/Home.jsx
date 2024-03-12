import React from 'react'
import "./styles/stylesheet.css"
import Sidebar from './Sidebar'
import Navbar from './Navbar'
import ProjectSection from './ProjectSection'

function Home() {
  return (
    <>
       <div className="page-row">
           <Sidebar/>
           <div className="project-wrapper">
              <Navbar/> 
              <ProjectSection/>
           </div>
       </div>
       
    </>
  )
}

export default Home