<template>
  <b-alert variant="success" v-if="plans.length == 0" show>You have no plans yet</b-alert>
  <b-card id="plans" no-body v-else>
    <b-card-header header-tag="header" class="p-0 mt-1">
      <b-btn block href="#" v-b-toggle.myPlans variant="info" class="text-left">{{headers.myPlans}}</b-btn>
    </b-card-header>
    <b-collapse id="myPlans" visible>
      <b-tabs card>
        <b-tab v-for="plan in plans" :key="plan.name" :title="plan.name">
          <b-containe>
            <b-row>
              <b-col class="bt bl br"></b-col>
              <b-col v-for="day in table.days" :key="day" class="bt br">{{day}}</b-col>
            </b-row>
            <b-row v-for="hour in table.hours" :key="hour">
              <b-col class="bl bt br">{{hour}}</b-col>
              <b-col v-for="day in table.days" :key="day" class="bt br hv">
              </b-col>
              <div class="w-100"></div>
              <b-col class="bl br"></b-col>
              <b-col v-for="day in table.days" :key="day" class="br btd hv"></b-col>
            </b-row>
          </b-containe>
        </b-tab>
      </b-tabs>
    </b-collapse>
  </b-card>
</template>

<script>
export default {
  data() {
    return {
      table: {
        days: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
        hours: ["12:00 AM", "1:00 AM", "2:00 AM", "3:00 AM", "4:00 AM", "5:00 AM"]
      },

      plans: [
        {
          name: "plan1",
        },
        {
          name: "plan2",
        },
        {
          name: "plan3",
        }
      ],
      headers: {
        myPlans: ''
      }
    }
  },
  computed: {
    language() {
      return this.$store.getters.language;
    }
  },
  mounted() {
    this.chooseLanguage(this.language);
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
          this.headers.myPlans = "Moje Plany";
        break;
        case "en":
          this.headers.myPlans = "My Plans";
        break;
      }
    },
    test() {
      console.log("kappa");
    }
  }
}
</script>

<style lang="css">
.col {
    text-align: center;
    vertical-align: middle;
    line-height: 50px;
    height: 50px;
}
.hv {
  background-color: #fafafa;
}
.hv:hover {
  background-color: #f0f0f0;
  cursor: pointer;
}
.bt {
  border-top: 1px solid #dadada;
}
.bl {
  border-left: 1px solid #dadada;
}
.bb {
  border-bottom: 1px solid #dadada;
}
.br {
  border-right: 1px solid #dadada;
}
.btd {
  border-top: 1px dotted #dadada;
}
.double-row {
  height: 50px;
}
</style>