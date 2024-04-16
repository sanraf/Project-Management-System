import React from 'react'
import Sidebar from './Sidebar'
import Navbar from './Navbar';


function Users() {
  return (
      <>
          <div className="page-row">
              <Sidebar />
              <div className="project-wrapper">
                  <Navbar />
                  <div className="users_dashboard page_row">
                      <div className="admin-users">
                      <div className="">
                           <div className='admin_graph_titles'>
                                  <h2>Users table</h2>
                                  <p>List of all the users in the system</p>
                              </div>
                          <div className="users_table">
                             
                              <div className="user_table_rows user_table_col_header">
                                  <div className='col_numbers user_tables_cols user_table_colNames'>
                                       <h6>User id</h6>
                                  </div>
                                  <div className='user_tables_cols user_table_colNames'>
                                      <h6>User details </h6>
                                  </div>
                                  <div className='user_tables_cols user_table_colNames'>
                                       <h6>Creation date</h6>
                                  </div>
                                  <div className='user_tables_cols user_table_colNames'>
                                        <h6>Last login</h6>
                                  </div>   
                                  <div className='user_tables_cols user_table_colNames'>
                                        <h6>Account status</h6>
                                  </div>  
                                  <div className='col_numbers user_tables_cols user_table_colNames'>
                                        <h6>Action</h6>
                                  </div> 
                              </div>
                              <div className="user_table_rows user_table_row_details">
                                  <div className="col_numbers user_id user_tables_cols">
                                      <p>02</p>
                                  </div>
                                  <div className="user_details user_tables_cols">
                                      <span className='user_letter'>J</span>
                                      <p>
                                          <span>Jane doe</span>
                                         <span> email@emai.com</span>
                                      </p>
                                  </div>
                                 
                                  <div className="date user_tables_cols">
                                    
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="date  user_tables_cols">
                               
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="project_status user_tables_cols">
                                      <p id='active'>active</p>
                                  </div>
                                  <div className="actions col_numbers user_tables_cols">
                                      <i className="lni lni-pencil-alt"></i> 
                                      <i class="lni lni-circle-minus"></i>
                                  </div>

                              </div>
                               <div className="user_table_rows user_table_row_details">
                                  <div className="col_numbers user_id user_tables_cols">
                                      <p>02</p>
                                  </div>
                                  <div className="user_details user_tables_cols">
                                      <span className='user_letter'>J</span>
                                      <p>
                                          <span>Jane doe</span>
                                         <span> email@emai.com</span>
                                      </p>
                                  </div>
                                  <div className="date user_tables_cols">
                                    
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="date  user_tables_cols">
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="project_status user_tables_cols">
                                      <p id='deactive'>Deactivated</p>
                                  </div>
                                  <div className="actions col_numbers user_tables_cols">
                                      <i className="lni lni-pencil-alt"></i> 
                                      <i class="lni lni-circle-minus"></i>
                                  </div>

                                  </div>
                                      <div className="user_table_rows user_table_row_details">
                                  <div className="col_numbers user_id user_tables_cols">
                                      <p>02</p>
                                  </div>
                                  <div className="user_details user_tables_cols">
                                      <span className='user_letter'>J</span>
                                      <p>
                                          <span>Jane doe</span>
                                         <span> email@emai.com</span>
                                      </p>
                                  </div>
                                 
                                  <div className="date user_tables_cols">
                                    
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="date  user_tables_cols">
                               
                                      <p>
                                          <span>12/02/25</span>
                                          <span>2:30pm</span>
                                      </p>
                                  </div>
                                  <div className="project_status user_tables_cols">
                                      <p id='active'>active</p>
                                  </div>
                                  <div className="actions col_numbers user_tables_cols">
                                      <i className="lni lni-pencil-alt"></i> 
                                      <i class="lni lni-circle-minus"></i>
                                  </div>

                              </div>
                          </div>
                            <div className="user_table_pages">
                                <div className="tables_total">
                                      <p>Showing 1-6 results</p>
                                      <i class="lni lni-arrow-right"></i>
                              </div>
                              <div className="user_page_slider">
                                  <span>next</span>
                                  <p className='show'>1</p>
                                  <p>2</p>
                                  <p>3</p>
                                  <span>prev</span>
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

export default Users