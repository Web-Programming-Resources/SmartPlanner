<template>
  <b-row class="text-left align-items-center">
    <b-col cols="3" class="mt-4 ml-auto mr-auto">
      <b-card>
        <b-form @submit="onSubmit">
          <b-form-group :label="form.headers.username" label-for="username">
            <b-form-input id="username" type="text" v-model="form.inputs.username" required :placeholder="form.headers.enterUsername">
            </b-form-input>
          </b-form-group>
          <b-form-group :label="form.headers.password" label-for="password">
            <b-form-input id="password" type="password" v-model="form.inputs.password" required :placeholder="form.headers.enterPassword">
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">{{form.headers.button}}</b-button>
        </b-form>
      </b-card>
    </b-col>
  </b-row>
</template>

<script>
export default {
  data() {
    return {
      form: {
        inputs: {
          username: '',
          password: ''
        },
        headers: {
          username: '',
          enterUsername: '',
          password: '',
          enterPassword: '',
          button: ''
        }
      },
    }
  },
  created() {
      this.chooseLanguage(this.language);
  },
  computed: {
    language() {
      return this.$store.getters.language;
    }
  },
  watch: {
    language (newVal, oldVal) {
      this.chooseLanguage(newVal);
    }
  },
  methods: {
    chooseLanguage(lang) {
      switch(lang) {
        case "pl":
              this.form.headers.username = "Nazwa Użytkownika";
              this.form.headers.enterUsername = "Podaj nazwę";
              this.form.headers.password = "Hasło";
              this.form.headers.enterPassword = "Podaj hasło";
              this.form.headers.button = "Zaloguj";
        break;
        case "en":
              this.form.headers.username = "Username";
              this.form.headers.enterUsername = "Enter username";
              this.form.headers.password = "Password";
              this.form.headers.enterPassword = "Enter password";
              this.form.headers.button = "Login";
        default:
        break;
      }
    },
    onSubmit(evt) {
      evt.preventDefault();
      var data = {
          accessToken: "1",
          username: this.form.username,
      }
      this.$store.commit('login', data);
      this.$router.push({path: '/'});
    }
  }
}
</script>