<template>
  <div>
    <b-card id="inputs" class="mb-2" no-body>
      <b-card-header header-tag="header" class="p-0">
        <b-btn block href="#" v-b-toggle.userInputs variant="info" class="text-left">{{headers.settings}}</b-btn>
      </b-card-header>
      <b-collapse id="userInputs" visible>
        <b-card-body>
          <b-row>
            <b-col cols="4">
              <table class="table table-sm color mt-0 p-0">
                <caption class="caption table-caption">{{headers.settings}}</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.numOfWeeks}}</th>
                    <td class="text-right">
                      <b-form-input id="numOfWeeks" v-model="inputs.numOfWeeks" size="sm" class="text-right" type='number' :state="positiveNumberState(this.inputs.numOfWeeks)"></b-form-input>
                      <b-form-invalid-feedback id="numOfWeeksFeedback">"{{headers.validators.positiveVal}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.maxCommutes}}</th>
                    <td class="text-right">
                      <b-form-input id="maxCommutes" v-model="inputs.maxCommutes" size="sm" class="text-right" type='number' :state="positiveNumberState(this.inputs.maxCommutes)"></b-form-input>
                      <b-form-invalid-feedback id="maxCommutesFeedback">"{{headers.validators.positiveVal}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.activityStart}}</th>
                    <td class="text-right">
                      <b-form-input id="activityStart" v-model="inputs.activityStart" size="sm" class="text-right" type='time' :state="notNullState(this.inputs.activityStart)"></b-form-input>
                      <b-form-invalid-feedback id="activityStartFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.activityEnd}}</th>
                    <td class="text-right">
                      <b-form-input id="activityEnd" v-model="inputs.activityEnd" size="sm" class="text-right"  type='time' :state="notNullAndBeforeState(this.inputs.activityStart, this.inputs.activityEnd)"></b-form-input>
                      <b-form-invalid-feedback id="activityEndFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>  
                    <tr>
                    <th class="text-left font-weight-normal">{{headers.minTimeAtActivity}}</th>
                    <td class="text-right">
                      <b-form-input id="minTimeAtActivity" v-model="inputs.minTimeAtActivity" size="sm" class="text-right" type='time' :state="notNullState(this.inputs.minTimeAtActivity)"></b-form-input>
                      <b-form-invalid-feedback id="minTimeAtActivityFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>      
                    <tr>
                    <th class="text-left font-weight-normal">{{headers.maxTimeAtActivity}}</th>
                    <td class="text-right">
                      <b-form-input id="maxTimeAtActivity" v-model="inputs.maxTimeAtActivity" size="sm" class="text-right" type='time' :state="notNullState(this.inputs.maxTimeAtActivity)"></b-form-input>
                      <b-form-invalid-feedback id="maxTimeAtActivityFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>                      
                </tbody>
              </table>
            </b-col>
            <b-col cols="4">
              <table class="table table-sm color mt-0 p-0">
                <caption class="caption table-caption">{{headers.addActivity}}</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonName}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonName" v-model="inputs.lessonName" size="sm" class="text-right" :state="notNullState(this.inputs.lessonName)"></b-form-input>
                      <b-form-invalid-feedback id="lessonNameFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonDay}}</th>
                    <td class="text-right">
                      <b-form-select id="lessonDay" v-model="inputs.lessonDay" size="sm" class="text-right" :options="inputs.days"  :state="notNullState(this.inputs.lessonDay)"></b-form-select>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.repeatEvery}}</th>
                    <td class="text-right">
                      <b-form-input id="repeatEvery" v-model="inputs.repeatEvery" size="sm" class="text-right" type='number'  :state="repeatEveryState"></b-form-input>
                      <b-form-invalid-feedback id="repeatEveryFeedback">"{{headers.validators.repeatEvery}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.startFromWeek}}</th>
                    <td class="text-right">
                      <b-form-input id="startFromWeek" v-model="inputs.startFromWeek" size="sm" class="text-right" type='number'  :state="startFromWeekState"></b-form-input>
                      <b-form-invalid-feedback id="startFromWeekFeedback">"{{headers.validators.repeatEvery}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonStartAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonStartAt" v-model="inputs.lessonStartAt" size="sm" class="text-right"  type='time' :state="notNullState(this.inputs.lessonStartAt)"></b-form-input>
                      <b-form-invalid-feedback id="lessonNameFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonEndsAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonEdnsAt" v-model="inputs.lessonEndsAt" size="sm" class="text-right"  type='time' :state="notNullState(this.inputs.lessonEndsAt)"></b-form-input>
                      <b-form-invalid-feedback id="lessonNameFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>                  
                </tbody>
              </table>
                <b-button type="submit" variant="primary" class="float-right" @click="addActivity()">{{headers.addButton}}</b-button>
            </b-col>
            <b-col cols="3">
              <table class="table table-sm color mt-0 p-0">
                <caption class="caption table-caption">{{headers.removeActivity}}</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonId}}</th>
                    <td class="text-right">
                      <b-form-input id="numOfWeeks" v-model="inputs.lessonId" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>                
                </tbody>
              </table>
                <b-button type="submit" variant="danger" class="float-right" @click="removeActivity()">{{headers.removeButton}}</b-button>
            </b-col>
            <b-button type="submit" variant="primary" class="float-right" @click="generatePlan()">{{headers.generateButton}}</b-button>
          </b-row>
        </b-card-body>
      </b-collapse>
    </b-card>
    <b-card id="activities" no-body>
      <b-card-header header-tag="header" class="p-0">
        <b-btn block href="#" v-b-toggle.activities variant="info" class="text-left">{{headers.activities}}</b-btn>
      </b-card-header>
      <b-collapse id="activities" visible>
         <b-table striped hover :items="lessons"></b-table>
      </b-collapse>
    </b-card>
  </div>
</template>

<script>
import moment from "moment";
import service from "../../services/HttpRequestService";

export default {
  data() {
    return {
      inputs: {
        numOfWeeks: 1,
        maxCommutes: 1,
        activityStart: '08:00',
        activityEnd: '16:00',
        minTimeAtActivity: '03:00',
        maxTimeAtActivity: '08:00',
        lessonName: 'lesson',
        lessonDay: '',
        repeatEvery: 1,
        startFromWeek: 1,
        lessonStartAt: '08:00',
        lessonEndsAt: '08:45',
        lessonId: '',
        days: []
      },
      headers: {
        settings: '',
        addActivity: '',
        addButton: '',
        numOfWeeks: '',
        maxCommutes: '',
        activityStart: '',
        activityEnd: '',
        minTimeAtActivity: '',
        maxTimeAtActivity: '',
        lessonName: '',
        lessonDay: '',
        repeatEvery: '',
        startFromWeek: '',
        lessonStartAt: '',
        lessonEndsAt: '',
        lessonId: '',
        removeButton: '',
        generateButton: '',
        validators: {
          numOfWeeks: '',
          maxCommutes: '',
          activityStart: '',
          activityEnd: '',
          minTimeAtActivity: '',
          maxTimeAtActivity: '',
          repeatEvery: '',
        }
      },
      lessons: [],
      idCount: 0,
    }
  },
  computed: {
    language() {
      return this.$store.getters.language;
    },
    repeatEveryState() {
      return this.inputs.repeatEvery <= this.inputs.numOfWeeks && this.positiveNumberState(this.inputs.repeatEvery);
    },
    startFromWeekState() {
      return this.inputs.startFromWeek <= this.inputs.numOfWeeks && this.positiveNumberState(this.inputs.repeatEvery);      
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
    notNullState(hour) {
      return hour !== "";
    },
    notNullAndBeforeState(lhs, rhs) {
      return this.notNullState(lhs) && this.notNullState(rhs) && moment(lhs, 'HH:mm').isBefore(moment(rhs, 'HH:mm'), 'mminute');
    },
    positiveNumberState(val) {
      return val > 0;
    },
    chooseLanguage(lang) {
      switch(lang) {
        case "pl":
          this.headers.settings = "Ustawienia";
          this.headers.addActivity = "Dodaj lekcje";
          this.headers.activities = "Dodane lekcje";
          this.headers.addButton = "Dodaj";
          this.headers.numOfWeeks = "Liczba tygodni w cyklu";
          this.headers.maxCommutes = "Max ilość dojazdow dziennie";
          this.headers.activityStart = "Praca otwiera sie o";
          this.headers.activityEnd = "Praca zamyka sie o";
          this.headers.minTimeAtActivity = "Minimalny jednorazowy czas w pracy";
          this.headers.maxTimeAtActivity = "Maksymalny jednorazowy czas w pracy";
          this.headers.lessonName = "Nazwa"
          this.headers.lessonDay = "Dzień"
          this.headers.repeatEvery = "Powtarzaj co n tygodni",
          this.headers.startFromWeek = "Zaczynając od tygodnia",
          this.headers.lessonStartAt = "Początek",
          this.headers.lessonEndsAt = "Koniec",
          this.headers.removeActivity = "Usuń lekcje",
          this.headers.lessonId = "ID",
          this.headers.removeButton = "Usun",
          this.inputs.days = ["Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"];
          this.inputs.lessonDay = this.inputs.days[0];
          this.headers.validators.notNull = "Pole nie może być puste";
          this.headers.validators.positiveVal = "Wartość musi być > 0";
          this.headers.validators.repeatEvery = "Wartość musi być <= liczby tygodni w cyklu oraz > 0";
          this.headers.generateButton = "Generuj"
        break;
        case "en":
          this.headers.settings = "Settings";
          this.headers.addActivity = "Add lesson";
          this.headers.activities = "Added lessons";
          this.headers.addButton = "Add";
          this.headers.numOfWeeks = "Weeks in cycle";
          this.headers.maxCommutes = "Max commutes per day";
          this.headers.activityStart = "Work opens at";
          this.headers.activityEnd = "Work closes at";
          this.headers.minTimeAtActivity = "Min time at work at once";
          this.headers.maxTimeAtActivity = "Max time at work at once";
          this.headers.lessonName = "Name"
          this.headers.lessonDay = "Day"
          this.headers.repeatEvery = "Repeat every n weeks",
          this.headers.startFromWeek = "Starting from week",
          this.headers.lessonStartAt = "Start",
          this.headers.lessonEndsAt = "End",
          this.headers.removeActivity = "Remove lesson",
          this.headers.lessonId = "ID",
          this.headers.removeButton = "Usun"
          this.inputs.days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
          this.inputs.lessonDay = this.inputs.days[0];
          this.headers.validators.numOfWeeks = "Value must be > 0";
          this.headers.validators.notNull = "Field cannot be empty";
          this.headers.validators.repeatEvery = "Value must be <= weeks in cycle and > 0";
          this.headers.removeButton = "Generate"
        break;
      }
    },
    addActivity() {
      console.log(this.inputs.activityStart);
      ++this.idCount;
      this.lessons.push({Id: this.idCount, Name: this.inputs.lessonName, Day: this.inputs.lessonDay, repeat_every:  this.inputs.repeatEvery, start: this.inputs.lessonStartAt, end: this.inputs.lessonEndsAt });
    },
    removeActivity() {
      for(var i = 0; i < this.lessons.length; ++i) {
          console.log(this.lessons[i].Id, ", ", this.inputs.lessonId)
        if (this.lessons[i].Id == this.inputs.lessonId) {
           this.lessons.splice(i,1);
           console.log("done");
        }
      }
    },
    generatePlan() {
      var body = {
  "planName" : "Plan #1",
  "daysInCycle": 5,
  "maxCommutesPerDay": 5,
  "optimizedActivity": {
  	"id" : 0,
    "name": "Work #1",
    "startsAt": "08:00",
    "endsAt": "16:00",
    "minTimeInMinutes": 120,
    "maxTimeInMinutes": 360,
    "isOpenedInDay": [
      true,
      true,
      false,
      true,
      false
    ]
  },
  "lessons": [
    {
      "id": 0,
      "name": "Lesson #1",
      "repeatingPeriod": 7,
      "possibleTerms": [
        {
          "startTime": "09:00",
          "durationInMinutes": 60,
          "cycleDayNumber": 4
        },
        {
          "startTime": "12:00",
          "durationInMinutes": 60,
          "cycleDayNumber": 4
        }
      ]
    },
    {
      "id": 1,
      "name": "Lesson #2",
      "repeatingPeriod": 7,
      "possibleTerms": [
        {
          "startTime": "13:30",
          "durationInMinutes": 60,
          "cycleDayNumber": 4
        },
        {
          "startTime": "16:00",
          "durationInMinutes": 60,
          "cycleDayNumber": 4
        }
      ]
    }
  ],
  "timeDistanceInMinutes": [
    [
      10,
      10,
      10
    ],
    [
      20,
      20,
      20
    ],
    [
      30,
      30,
      30
	]
  ]
};
      var res = service.post("http://localhost:8080/api/plans", body);
      console.log("Eureka");
      console.log("res");
    }
  }
}
</script>
<style lang="css">

</style>