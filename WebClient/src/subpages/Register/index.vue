<template>
    <b-row class="text-left align-items-center">
      <b-col cols="7" class="mt-3 ml-auto mr-auto">
        <b-form @submit="onSubmit">
            <table class="table-sm color mb-2">
                <tbody>
                    <tr>
                        <th class="text-left font-weight-normal">{{form.headers.username}}</th>
                        <td class="text-right">
                            <b-form-input id="register_username" class="add-user-input text-left" v-model="form.inputs.username" required :placeholder="form.headers.enterUsername"></b-form-input>
                        </td>
                    </tr>
                    <tr>
                        <th class="text-left font-weight-normal">{{form.headers.password}}</th>
                        <td class="text-right">
                            <b-input-group>
                                <b-form-input id="register_password" class="add-user-input text-left" :type="passwordType" v-model="form.inputs.password" required :placeholder="form.headers.enterPassword"></b-form-input>
                                <b-btn variant="info" size="sm" @mousedown="passwordType = 'text'" @mouseup="passwordType = 'password'">&#934;</b-btn>
                            </b-input-group>
                        </td>
                    </tr>
                    <tr>
                        <th class="text-left font-weight-normal">{{form.headers.confirmPassword}}</th>
                        <td class="text-right">
                            <b-input-group>
                                <b-form-input id="register_confirmPassword" class="add-user-input text-left" :type="passwordType" v-model="form.inputs.confirmPassword" required :placeholder="form.headers.confirmPassword"></b-form-input>
                                <b-btn variant="info" size="sm" @mousedown="passwordType = 'text'" @mouseup="passwordType = 'password'">&#934;</b-btn>
                            </b-input-group>
                        </td>
                    </tr>
                    <tr>
                        <th class="text-left font-weight-normal">{{form.headers.email}}</th>
                        <td class="text-right">
                            <b-form-input id="register_email" class="add-user-input text-left" v-model="form.inputs.email" required :placeholder="form.headers.enterEmail"></b-form-input>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <b-alert :show="showAlert" variant="danger">{{this.form.headers.wrongEmail}}</b-alert>
                        </th>
                        <td class="text-right">
                            <b-button type="submit" variant="primary">{{form.headers.registerButton}}</b-button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </b-form>
      </b-col>
    </b-row>
</template>

<script>
import Register from "../Register/"
import listener from "../../utilities/listeners"

export default {
  data() {
    return {
        passwordType:'password',
        showAlert: false,
        errorMsg: '',
      form: {
        inputs: {
          username: '',
          password: '',
          confirmPassword:'',
          email:''
        },
        headers: {
          username: '',
          enterUsername: '',
          password: '',
          email: '',
          enterEmail:'',
          enterPassword: '',
          loginButton: '',
          registerButton:'',
          emptyInput: '',
          wrongEmail: ''
        }
      },
    }
  },
  mounted() {
    this.chooseLanguage(this.$store.getters.language);
    listener.addValidityListeners(document, ['register_username', 'register_password', 'register_confirmPassword', 'register_email'], this.form.headers.emptyInput);
  },

  methods: {
    onSubmit(evt) {
        if (event) evt.preventDefault();
        if(this.validateInputs())
        {
            this.showAlert = false;
            //TODO sk: change when databese will be ready
            this.$root.$data.username = this.form.inputs.username;
            this.$root.$data.password = this.form.inputs.password;
            this.$emit('registered');
        }
        else
        {
            this.showAlert = true;
        }

    },
    validateInputs() {
        return this.validateEmail(this.form.inputs.email);
    },
    validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email).toLowerCase());
    },
    chooseLanguage(lang) {
      switch(lang) {
        case "pl":
            this.form.headers.username = "Nazwa Użytkownika";
            this.form.headers.enterUsername = "Podaj nazwę";
            this.form.headers.password = "Hasło";
            this.form.headers.confirmPassword = "Potwierdź hasło";
            this.form.headers.enterPassword = "Podaj hasło";
            this.form.headers.email = "Adres email";
            this.form.headers.enterEmail = "Podaj email";
            this.form.headers.registerButton = "Zarejestruj";
            this.form.headers.emptyInput = "Uzupełnij to pole.";
            this.form.headers.wrongEmail = "Nieprawidłowy adres email.";
        break;
        case "en":
            this.form.headers.username = "Username";
            this.form.headers.enterUsername = "Enter username";
            this.form.headers.confirmPassword = "Confirm password";
            this.form.headers.enterPassword = "Enter password";
            this.form.headers.password = "Password";
            this.form.headers.email = "Email address";
            this.form.headers.enterEmail = "Enter email";
            this.form.headers.registerButton = "Register";
            this.form.headers.emptyInput = "Fill this field.";
            this.form.headers.wrongEmail = "Incorrect email address.";
        default:
        break;
      }
    },
  }
}
 </script>
