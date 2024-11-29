import axios from 'axios';
import router from "@/router";

// Create a new axios instance
const request = axios.create({
    baseURL: process.env.VUE_APP_BASEURL,   // Backend API address (ip:port)
    timeout: 30000                         // Request timeout of 30 seconds
});

// Request interceptor
// Allows processing before a request is sent, such as adding a token or encrypting request parameters
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8'; // Set the request header format
    let user = JSON.parse(localStorage.getItem("xm-user") || '{}');    // Retrieve cached user information
    config.headers['token'] = user.token;                             // Set the token in the request header

    return config;
}, error => {
    console.error('request error: ' + error); // for debugging
    return Promise.reject(error);
});

// Response interceptor
// Allows unified handling of the response results after the API call
request.interceptors.response.use(
    response => {
        let res = response.data;

        // Handle cases where the server returns string data
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res;
        }
        if (res.code === '401') { // Redirect to the login page if unauthorized
            router.push('/login');
        }
        return res;
    },
    error => {
        console.error('response error: ' + error); // for debugging
        return Promise.reject(error);
    }
);

export default request;
