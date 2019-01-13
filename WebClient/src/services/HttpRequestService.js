import axios from "axios";

const domain = window.BASE_URL

axios.defaults.baseURL = domain;

function createAuthHeader(){
  return {
    headers: {
      'Authorization': 'Bearer ' + store.getters.token
    }
  }
}

export default {
  get(url) {
    return axios.get(url, createAuthHeader());
  },
  post(url, body) {
    return axios.post(url, body);
  },
}
