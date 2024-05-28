import React,{useEffect,useState} from 'react'
import { Outlet, Navigate, useHref } from "react-router-dom";
import { refreshToken } from "./RefreshToken";

function LoginRoute() {
    const fetchData = async () => {
      try {
        const userFound = await refreshToken();
          if (userFound) {
            window.location.href = "/home"
        }
      } catch (error) {
          console.error('Error fetching data:', error)
          window.location.href = "/login"
      }
    }
    fetchData();
}

export default LoginRoute