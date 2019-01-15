import axios from "axios";
import store from "../store";

const domain = "http://localhost:8080"

function createAuthHeader(){
  return {
    headers: {
      'Authorization': 'Bearer ' + store.getters.token
    }
  }
}

export default {
  get(url) {
    return axios.get(domain + url, createAuthHeader());
  },
  post(url, body) {
    return axios.post(domain + url, body, createAuthHeader());
  },
  postWithoutToken(url, body) {
    return axios.post(domain + url, body);
  },
}
