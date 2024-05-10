import React from 'react'
import Navbar from './Navbar';
import Sidebar from './Sidebar';

function AccountDeactivate() {
  return (
      <>
          <div className="page-row">
                <Sidebar/>
              <div className="project-wrapper">
                  <Navbar />
                  <div className="disable_section guide-section">
                      <div className="container page-row">
                          <div className="user_info_disable">
                              <h6>User Details</h6>
                              <div className='user_info'>
                                  <span className='task_assign_letter'>LM</span>
                                  <div className='user_info_text'>
                                      <p>John doe </p>
                                      <p>email@email.com </p>
                                  </div>
                                   <div className="disable_user_stats page-row">
                                  <div className='user_system_stats'>
                                    <p>Total Project </p>
                                    <p>300</p>
                                  </div>
                                    <div className='user_system_stats'>
                                    <p>Total Tasks </p>
                                    <p>300</p>
                                  </div>
                             </div>
                              </div>
                             
                          </div>
                          <div className='disable_details'>
                              <h4>Disabling User  Account</h4>
                              <p>We are sad to see you leave </p>
                              <h5>Before you leave please note the following:</h5>
                              <div className="disable_notices">
                                   <p>Disabling your account will prevent access to all associated features and content.</p>
                              <p>You won't be able to retrieve any data or information tied to the account once it's disabled.</p>
                              <p>Once disabled, you may lose access to communication channels and support options associated with the account.</p>
                                  <p>Be aware that disabling an account might impact your ability to utilize certain platform features or services in the future.</p>
                                  <p>Make sure to download or backup any crucial data before disabling your account.</p>
                              </div>
                              <button>Disable account</button>
                          </div>
                     </div>
                 </div>
              </div>
          </div>
          
      </>
  )
}

export default AccountDeactivate