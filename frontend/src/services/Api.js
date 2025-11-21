import axios, { HttpStatusCode } from "axios";

let PORT   = import.meta.env.VITE_APP_PORT_SERVER || '';
let SERVER = import.meta.env.VITE_APP_URL_SERVER;

// Create a instance of url to server backend
const url_server = PORT ? `${SERVER}:${PORT}` : `${SERVER}`
console.log(url_server)
// Create a instance of axios to use the same base url.
const axiosAPI = axios.create({
  baseURL : url_server
});

// implement a method to execute all the request from here.
const apiRequestMultipart = async (method, url, request) => {
  // const headers = { "Content-Type": "multipart/form-data"  };
  const headers = {};
  
  return axiosAPI({
      method,
      url,
      data: request,
      headers
    }).then(res => {
      return Promise.resolve(res.data);
    })
    .catch(err => {
      return Promise.reject(err);
    });
};

// implement a method to execute all the request from here.
const apiRequest = async (method, url, request) => {
    const headers = { 'Content-Type': 'application/json', Accept: 'application/json' };
    
    return axiosAPI({
        method,
        url,
        data: request,
        headers
      }).then(res => {
        return Promise.resolve({
            data: res.data,
            status: res.status,
        });
      })
      .catch(err => {
        return Promise.reject(err);
      });
};

// function to execute the http get request
const get = (url, request) => apiRequest("get", url, request);

// function to execute the http delete request
const deleteRequest = (url, request) =>  apiRequest("delete", url, request);

// function to execute the http post request
const post = (url, request) => apiRequest("post", url, request);

// function to execute the http post request
const postMultipart = (url, request) => apiRequestMultipart("post", url, request);

// function to execute the http put request
const put = (url, request) => apiRequest("put", url, request);

// function to execute the http patch request
const patch = (url, request) =>  apiRequest("patch", url, request);


const downloadBlob = async (url) => {
  const base_url = url_server + url;
  try {
      const response = await axios.get(base_url, { responseType: 'blob' });
      const pdfBlob = new Blob([response.data], { type: 'application/pdf' });
      if (response.status == HttpStatusCode.Ok ){
          return pdfBlob;
      }
      else {
        return null;
      }
  } catch (error) {
      console.error('Error downloading PDF:', error);
      return null;
  }    
}

// Download file function
const downloadFile = async (url, filename) => {
  try {
    const response = await axiosAPI.get(url, { responseType: 'blob' });
    const url_d = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url_d;

    // You may want to derive filename from content-disposition header
    const contentDisposition = response.headers['content-disposition'];
    // let filename = 'download';
    if (contentDisposition) {
      const fileNameMatch = contentDisposition.match(/filename="?(.+)"?/);
      if (fileNameMatch.length === 2) filename = fileNameMatch[1];
    }

    link.setAttribute('download', filename);
    document.body.appendChild(link);
    link.click();
    link.parentNode.removeChild(link);
    window.URL.revokeObjectURL(url_d);
  } catch (error) {
    console.error('Download failed:', error);
  }
};


// expose your method to other services or actions
const API = {
    get,
    delete: deleteRequest,
    post,
    put,
    patch,
    postMultipart,
    downloadFile,
    downloadBlob,
    axiosAPI,
};



export default API;
