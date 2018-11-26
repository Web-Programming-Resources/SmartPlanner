<template>
  <div>
    <b-card id="inputs" class="mb-2" no-body>
      <b-card-header header-tag="header" class="p-0">
        <b-btn block href="#" v-b-toggle.userInputs variant="info" class="text-left">{{headers.planInformation.name}}</b-btn>
      </b-card-header>
      <b-collapse id="userInputs" visible>
        <b-card-body>
          <b-row>
            <!-- <b-col cols="5">
              <table class="table table-sm color mt-0 p-0">
                <caption class="caption table-caption">Settings</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">Number of weeks</th>
                    <td class="text-right">
                      <b-form-input id="numOfWeeks" v-model="inputs.numOfWeeks" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">Max commutes per day</th>
                    <td class="text-right">
                      <b-form-input id="maxCommutes" v-model="inputs.maxCommutes" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">Activity starts at</th>
                    <td class="text-right">
                      <b-form-input id="activityStart" v-model="inputs.activityStart" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">Activity ends at</th>
                    <td class="text-right">
                      <b-form-input id="activityEnd" v-model="inputs.activityEnd" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>                  
                </tbody>
              </table>
            </b-col> -->
            <b-col cols="5">
              <table class="table table-sm color">
                <caption class="caption table-caption">{{headers.planInformation.name}}</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.planInformation.maxCommutesPerDay}}</th>
                    <td class="text-right">{{currentPlan.maxCommutesPerDay}}</td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.planInformation.workStartsAt}}</th>
                    <td class="text-right">{{currentPlan.optimizedActivityStartsAt}}</td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.planInformation.workEndsAt}}</th>
                    <td class="text-right">{{currentPlan.optimizedActivityEndsAt}}</td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.planInformation.minTimeAtWork}}</th>
                    <td class="text-right">{{currentPlan.minTimeSpentAtOptimizedActivityAtOnceInMinutes}}</td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.planInformation.maxTimeAtWork}}</th>
                    <td class="text-right">{{currentPlan.maxTimeSpentAtOptimizedActivityAtOnceInMinutes}}</td>
                  </tr>
                </tbody>
              </table>
            </b-col>
          </b-row>
        </b-card-body>
      </b-collapse>
    </b-card>
    <b-card id="plans" no-body>
      <b-card-header header-tag="header" class="p-0">
        <b-btn block href="#" v-b-toggle.createPlan variant="info" class="text-left">{{headers.myPlans}}</b-btn>
      </b-card-header>
      <b-collapse id="createPlan" visible>
        <b-tabs card v-model="tabIndex">
          <b-tab v-for="plan in plans" :key="plan.name" :title="plan.name" v-on:click="changeCurrentPlan()">
            <b-container style="max-height:65vh;overflow-y:scroll;">
              <b-row>
                <b-col class="bt bl br"></b-col>
                <b-col v-for="day in table.days" :key="day" class="bt br">{{day}}</b-col>
              </b-row>
              <b-row v-for="hour in table.hours" :key="hour">
                <b-col class="bl bt br table-header" >{{hour}}</b-col>
                <b-col v-for="(day, index) in table.days" :key="hour+day" class="bt br hv table-header" >
                  <b-row v-for="quarter in (0,4)" :key="quarter" class = "row-quarter bt-dot hv" v-on:click="test()">
                    <div v-if="isLessonHeaderToDisplay(plan, index, hour, quarter, 1)" class="no-bg">{{currentLesson}}</div>
                    <b-col>
                      <b-row v-for="minute in (0,5)" :key="day+hour+quarter+minute" v-if="isLessonToDisplay(plan, index, hour, quarter, minute)" class="row-threemin lesson"></b-row>
                    </b-col>
                  </b-row>
                </b-col>
              </b-row>
            </b-container>
          </b-tab>
        </b-tabs>
      </b-collapse>
    </b-card>
  </div>
</template>

<script>
import moment from "moment"
import formatter from "../../utilities/formatter"

export default {
  data() {
    return {
      tabIndex: 0,
      currentPlan: '',
      table: {
        days: [],
        hours: ["00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00",
        "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
        "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00",
        ]
      },
      plans: [],
      headers: {
        planInformation: {
          name: '',
          maxCommutesPerDay: '',
          workStartsAt: '',
          workEndsAt: '',
          minTimeAtWork: '',
          maxTimeAtWork: ''
          },
        myPlans: '',
      },
    }
  },

  computed: {
    language() {
      return this.$store.getters.language;
    }
  },

  created() {
    this.getPlans();
    this.currentPlan = this.plans[0];
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
    test() {
      console.log("test");
    },
    chooseLanguage(lang) {
      switch(lang) {
        case "pl":
          this.headers.planInformation.name = "Informacje o planie";
          this.headers.planInformation.maxCommutesPerDay = "Maksymalna liczba dojazdów dziennie";
          this.headers.planInformation.workStartsAt = "Praca otwiera się o";
          this.headers.planInformation.workEndsAt = "Praca zamyka się o";
          this.headers.planInformation.minTimeAtWork = "Minimalny czas w pracy";
          this.headers.planInformation.maxTimeAtWork = "Maksymalny czas w pracy";
          this.headers.myPlans = "Moje Plany";
          this.table.days = ["Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"];
        break;
        case "en":
          this.headers.planInformation.name = "Plan information";
          this.headers.planInformation.maxCommutesPerDay = "Max commutes per day";
          this.headers.planInformation.workStartsAt = "Work opens at";
          this.headers.planInformation.workEndsAt = "Work closes at";
          this.headers.planInformation.minTimeAtWork = "Min time at work";
          this.headers.planInformation.maxTimeAtWork = "Max time at work";
          this.headers.myPlans = "My Plans";
          this.table.days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
        break;
      }
    },

    formatData(data) {
      for(let plan of data) {
        plan.minTimeSpentAtOptimizedActivityAtOnceInMinutes = formatter.minutesToHours(plan.minTimeSpentAtOptimizedActivityAtOnceInMinutes);
        plan.maxTimeSpentAtOptimizedActivityAtOnceInMinutes = formatter.minutesToHours(plan.maxTimeSpentAtOptimizedActivityAtOnceInMinutes);
      }
    },

    getPlans() {
      //TODO sk: get plans from Web API
      var data = [
        {
          name: "plan1",
          daysInCycle: 14,
          maxCommutesPerDay: 2,
          optimizedActivityStartsAt: "08:00",
          optimizedActivityEndsAt: "20:00",
          minTimeSpentAtOptimizedActivityAtOnceInMinutes: 180,
          maxTimeSpentAtOptimizedActivityAtOnceInMinutes: 480,
          isOpenedInDay: [1,1,1,1,1,0,0,1,1,1,1,1,0,0],
          lessons: [
            {
              id: 0,
              name: "Inzynieria Programowania",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 0
              }
            },
            {
              id: 1,
              name: "Systemy Wbudowane",
              repeatingPeriod: 7,
              term:
              {
                startTime: "11:00",
                durationInMin: 90,
                cycleDayNumber: 0
              }
            },
            {
              id: 2,
              name: "Systemy Baz Danych",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 1
              }
            },
            {
              id: 3,
              name: "Symulacje Komputerowe",
              repeatingPeriod: 14,
              term:
              {
                startTime: "14:30",
                durationInMin: 90,
                cycleDayNumber: 8
              }
            },
            {
              id: 4,
              name: "Programowanie w Jezyku Java",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 2
              }
            },
            {
              id: 5,
              name: "Komputerowe Wspomaganie Decyzji",
              repeatingPeriod: 14,
              term:
              {
                startTime: "14:30",
                durationInMin: 90,
                cycleDayNumber: 3
              }
            },
          ]
        },
        {
          name: "plan2",
          daysInCycle: 14,
          maxCommutesPerDay: 2,
          optimizedActivityStartsAt: "10:00",
          optimizedActivityEndsAt: "20:00",
          minTimeSpentAtOptimizedActivityAtOnceInMinutes: 180,
          maxTimeSpentAtOptimizedActivityAtOnceInMinutes: 480,
          isOpenedInDay: [1,1,1,1,1,0,0,1,1,1,1,1,0,0],
          lessons: [
            {
              id: 0,
              name: "Inzynieria Programowania",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 0
              }
            },
            {
              id: 1,
              name: "Systemy Wbudowane",
              repeatingPeriod: 7,
              term:
              {
                startTime: "11:00",
                durationInMin: 90,
                cycleDayNumber: 0
              }
            },
            {
              id: 2,
              name: "Systemy Baz Danych",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 1
              }
            },
            {
              id: 3,
              name: "Symulacje Komputerowe",
              repeatingPeriod: 14,
              term:
              {
                startTime: "14:30",
                durationInMin: 90,
                cycleDayNumber: 8
              }
            },
            {
              id: 4,
              name: "Programowanie w Jezyku Java",
              repeatingPeriod: 14,
              term:
              {
                startTime: "09:15",
                durationInMin: 90,
                cycleDayNumber: 2
              }
            },
            {
              id: 5,
              name: "Komputerowe Wspomaganie Decyzji",
              repeatingPeriod: 14,
              term:
              {
                startTime: "14:30",
                durationInMin: 90,
                cycleDayNumber: 3
              }
            },
          ]
        },
      ]
      this.formatData(data);
      this.plans = data;
    },
    changeCurrentPlan() {
      this.currentPlan = this.plans[this.tabIndex];
    },
    isLessonToDisplay(plan, dayNumber, hour, quarter, minute) {
      for(let lesson of plan.lessons) {
        if(dayNumber % 7 == lesson.term.cycleDayNumber % 7) {
            var curTime = moment(hour, 'HH:mm').add((quarter - 1) * 15 + (minute - 1) * 3, 'minutes');
            
            var startTime = moment(lesson.term.startTime, "HH:mm");
            var endTime = moment(startTime).add(lesson.term.durationInMin, 'minutes');
            if(curTime.isBetween(moment(startTime), moment(startTime).add(lesson.term.durationInMin, 'minutes'), 'minute', '[)'))
            {
              return true;
            }
        }
      }
      return false;
    },
    isLessonHeaderToDisplay(plan, dayNumber, hour, quarter, minute) {
      for(let lesson of plan.lessons) {
        if(dayNumber % 7 == lesson.term.cycleDayNumber % 7) {
            var curTime = moment(hour, 'HH:mm').add((quarter -1) * 15 + (minute - 1) * 3, 'minutes');
            var startTime = moment(lesson.term.startTime, "HH:mm");
            if(curTime.isBetween(moment(startTime), moment(startTime).add(15, 'minutes'), 'minute', '[)'))
            {
              this.currentLesson = lesson.name;
              return true;
            }
        }
      }
    }
  }
}
</script>

<style lang="css">
.table-caption {
  caption-side: top;
  text-align: center;
  padding: 0 0;
  color: #fff;
  background-color: #17a2b8;
}
.table-header {
  line-height: 60px !important;
  height: 60px !important;
}
.col {
    text-align: center;
    vertical-align: middle;
    line-height: 30px;
    height: 30px;
}
.row-quarter {
    text-align: center;
    vertical-align: middle;
    line-height: 15px;
    height: 15px;
}
.row-threemin {
    text-align: center;
    vertical-align: middle;
    line-height: 3px;
    height: 3px;
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
.bt-dot {
  border-top: 1px dotted #dadada;
}
.bb-dot {
  border-bottom: 1px dotted #dadada;
}
.lesson {
  background-color: #17a2b8 !important;
  z-index: 98 !important;
  position: absolute;
  width: 100%;
  height: 100%;
}
.no-bg {
  Font-Size: 13px;
  z-index: 99 !important;
  position: absolute !important;
  width: 100%;
}
</style>