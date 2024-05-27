import React from 'react'
import Sidebar from './Sidebar';
import Navbar from './Navbar';

function Feedback() {
  return (
      <>
          <div className="page-row">
            <Sidebar />
            <div className="project-wrapper">
                <Navbar />
                <div className="feedback_admin_section">
                    <h6>Users Systems feedback</h6>      
                    <div className="admin_feedback_wrapper">
                        <div className='admin_feedback_first_row'>
                              <div className="feedback_icons">
                                  <span className='all_checkmark'><i className="lni lni-checkmark"></i></span>
                                  <div className='feedback_icon_box'>
                                    <i className="lni lni-unlink"></i>
                                    <span style={{display:"none"}}>resolved </span>
                                  </div>
                                  <div className='feedback_icon_box'>
                                    <i className="lni lni-trash-can"></i>
                                    <span style={{display:"none"}}>delete</span>
                                  </div>
                                  <div className='feedback_icon_box'>
                                      <i className="lni lni-check-box"></i>
                                      <span style={{display:"none"}}>mark as read</span>
                                  </div>
                            </div>
                            <div className="feedback_pagenation">
                              <span className='page_no'>1 of 5</span>      
                            </div>  
                       </div>
                        <div className="admin_feedback_table">
                            <div className="feedback_user">
                                <span><i className="lni lni-checkmark"></i></span>
                                <p>john doe</p>
                            </div>
                            <div className="feedback_massage">
                                <p>Struggling to login into the system</p>
                            </div>
                            <div className="feedback_date">
                                <p><i className="lni lni-alarm-clock"></i> 3 April 2025</p>
                            </div>
                          </div>
                             <div className="admin_feedback_table">
                            <div className="feedback_user">
                                <span><i className="lni lni-checkmark"></i></span>
                                <p>john doe</p>
                            </div>
                            <div className="feedback_massage">
                                <p>Struggling to login into the system</p>
                            </div>
                            <div className="feedback_date">
                                <p> <i className="lni lni-alarm-clock"></i> 3 April 2025</p>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
          </div> 
        
    </>
  )
}

export default Feedback