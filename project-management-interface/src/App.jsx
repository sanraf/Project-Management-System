import CreateProject from "./Component/CreateProject"
import Guide from "./Component/Guide"
import ProjectSection from "./Component/ProjectSection";
import "./Component/styles/stylesheet.css"
import Login from "./Component/Login";
import Users from "./Component/Users";
import PrivateRoutes from "./Component/PrivateRoutes";
import LoginRoute from "./Component/LoginRoute";
import AccountDeactivate from "./Component/AccountDeactivate";

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route element={<PrivateRoutes/>}>
          <Route element={<Users />} path="/users" exact />
          <Route element={<Guide />} path="/help" exact />
          <Route element={<CreateProject />} path="/createProject" exact />
          <Route element={<ProjectSection />} path="/project" exact />
          <Route element={<AccountDeactivate />} path="/disable" exact />
        </Route>
        <Route path="/" element={<LoginRoute />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </Router>
  )
}

export default App
