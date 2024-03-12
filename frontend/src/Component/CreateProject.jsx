import React from 'react'
import Sidebar from './Sidebar'
import Navbar from './Navbar'

function CreateProject() {
  return (
    <>
    <div className="page-row">
           <Sidebar/>
           <div className="project-wrapper">
            <Navbar/> 
           </div>
       </div>
    </>
  )
}

export default CreateProject