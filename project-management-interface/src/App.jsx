import CreateProject from "./Component/CreateProject"
import Guide from "./Component/Guide"
import ProjectSection from "./Component/ProjectSection";
import "./Component/styles/stylesheet.css"
import Login from "./Component/Login";
import Users from "./Component/Users";
import PrivateRoutes from "./Component/PrivateRoutes";
import LoginRoute from "./Component/LoginRoute";
import AccountDeactivate from "./Component/AccountDeactivate";
import FeedbackAdmin from "./Component/FeedbackAdmin";
import FeedbackPage from "./Component/FeedbackPage"
import SettingsPage from './Component/SettingsPage';
import ProfileEditPage from './Component/ProfileEditPage';
import NotificationSettings from './Component/NotificationSettings';
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
        <Route path="/feedbackAdmin" element={<FeedbackAdmin />} />
        <Route path="/profileEditPage" element={<ProfileEditPage />} />
        <Route path="/settingspage" element={<SettingsPage />} />
        <Route path="/notificationsettings" element={<NotificationSettings />} />
        <Route path="/feedbackpage" element={<FeedbackPage />} />

      </Routes>
    </Router>
  )
}

export default App
