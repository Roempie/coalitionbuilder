import axios from 'axios';

class UserService {
 async register(user) {
   return axios.post(process.env.VUE_APP_API_URL + '/api/v1/auth/register', user);
 }

 async authenticate(user) {
   let email = user.email;
    let password = user.password;
   return axios.post(process.env.VUE_APP_API_URL + '/api/v1/auth/authenticate', {email, password});
 }

 async info(email,token) {
   return axios.get(process.env.VUE_APP_API_URL + '/api/v1/demo-controller?email='+email,
      {
         headers: {
            'Authorization': `Bearer ${token}` 
         }
      }
   );
 }
}

export default new UserService();