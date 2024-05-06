import { Outlet, Navigate, useHref } from "react-router-dom";
import React,{useEffect,useState} from "react";
import { useAuth } from "./AuthProvider";
import axios from 'axios';
import { refreshToken } from "./RefreshToken";


const PrivateRoutes = () => {
  const [user, setUser] = useState();
  const [redirect, setredirect] = useState();
  
  useEffect(() => {
    const fetchData = async () => {
      try {
        const userFound = await refreshToken();
        setUser(userFound)
        setredirect(userFound)
      } catch (error) {
       console.error('Error fetching data:', error)
      }
    }
    fetchData();
    return () => {}
   }, [user]);
  
  if(!redirect) {
    return <p>loading...</p>;
  }
    
  return(
    user ? <Outlet/>:<Navigate to={"/"} />
  );
}
export default PrivateRoutes;