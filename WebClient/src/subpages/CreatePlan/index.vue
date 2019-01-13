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
                    <th class="text-left font-weight-normal">{{headers.planName}}</th>
                    <td class="text-right">
                      <b-form-input id="planName" v-model="inputs.planName" size="sm" class="text-right" :state="notNullState(this.inputs.planName)"></b-form-input>
                      <b-form-invalid-feedback id="planNameFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
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
                      <b-form-invalid-feedback id="lessonStartAtback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonEndsAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonEdnsAt" v-model="inputs.lessonEndsAt" size="sm" class="text-right"  type='time' :state="notNullState(this.inputs.lessonEndsAt)"></b-form-input>
                      <b-form-invalid-feedback id="lessonEndsAtFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>                  
                </tbody>
              </table>
                <b-button type="submit" variant="primary" class="float-right" @click="addActivity()">{{headers.addButton}}</b-button>
            </b-col>
            <b-col cols="3">
              <table class="table table-sm color mt-0 p-0">
                <caption class="caption table-caption">{{headers.addTerm}}</caption>
                <tbody>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonId}}</th>
                    <td class="text-right">
                      <b-form-input id="numOfWeeks" v-model="inputs.addLessonId" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>  
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonStartAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonStartAt" v-model="inputs.addLessonStartAt" size="sm" class="text-right"  type='time' :state="notNullState(this.inputs.addLessonStartAt)"></b-form-input>
                      <b-form-invalid-feedback id="lessonStartAtFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonEndsAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonEdnsAt" v-model="inputs.addLessonEndsAt" size="sm" class="text-right"  type='time' :state="notNullState(this.inputs.addLessonEndsAt)"></b-form-input>
                      <b-form-invalid-feedback id="lessonEndsAtFeedback">"{{headers.validators.notNull}}"</b-form-invalid-feedback>
                    </td>
                  </tr>                
                </tbody>
              </table>
              <b-button type="submit" variant="primary" class="float-right mb-3" @click="addTerm()">{{headers.addButton}}</b-button>
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
        planName: 'plan',
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
        addLessonStartAt: '08:00',
        addLessonEndsAt: '08:45',
        addLessonId: '',
        lessonId: '',
        days: []
      },
      headers: {
        settings: '',
        addActivity: '',
        addButton: '',
        planName: '',
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
        removeActivity: '',
        addTerm: '',
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
          this.headers.planName = "Nazwa planu";
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
          this.headers.addTerm = "Dodaj termin",
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
          this.headers.planName = "Plan name";
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
          this.headers.addTerm = "Add term",
          this.headers.removeActivity = "Remove lesson",
          this.headers.lessonId = "ID",
          this.headers.removeButton = "Usun"
          this.inputs.days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
          this.inputs.lessonDay = this.inputs.days[0];
          this.headers.validators.numOfWeeks = "Value must be > 0";
          this.headers.validators.notNull = "Field cannot be empty";
          this.headers.validators.repeatEvery = "Value must be <= weeks in cycle and > 0";
          this.headers.generateButton = "Generate"
        break;
      }
    },
    addActivity() {
      this.lessons.push({Id: this.idCount, Name: this.inputs.lessonName, Day: this.inputs.lessonDay, repeat_every:  this.inputs.repeatEvery, start_from_week: this.inputs.startFromWeek, start: this.inputs.lessonStartAt, end: this.inputs.lessonEndsAt });
      ++this.idCount;
    },
    addTerm() {
       for(var i = 0; i < this.lessons.length; ++i) {
         if(this.lessons[i].Id == this.inputs.addLessonId) {
          var newTerm = JSON.parse(JSON.stringify(this.lessons[i]));
          newTerm.start = this.inputs.addLessonStartAt; 
          newTerm.end = this.inputs.addLessonEndsAt;
          this.lessons.push(newTerm);
          break;
         }
       }
    },
    removeActivity() {
      for(var i = 0; i < this.lessons.length; ++i) {
        if (this.lessons[i].Id == this.inputs.lessonId) {
           this.lessons.splice(i,1);
           --i;
        }
      }
    },
    getDayIndex(day) {
      for(var i = 0 ; i < 7; ++i) {
        if(this.inputs.days[i].localeCompare(day))
          return i;
      }
    },
    generatePlan() {
      var body = {
        "planName" : this.inputs.planName,
        "daysInCycle": parseInt(this.inputs.numOfWeeks * 7),
        "maxCommutesPerDay": parseInt(this.inputs.maxCommutes),
        "optimizedActivity": {
          "id" : 0,
          "name": "Work",
          "startsAt": this.inputs.activityStart,
          "endsAt":  this.inputs.activityEnd,
          "minTimeInMinutes":   moment(this.inputs.minTimeAtActivity, 'HH:mm').hour() * 60 + moment(this.inputs.minTimeAtActivity, 'HH:mm').minutes(),
          "maxTimeInMinutes":  moment(this.inputs.maxTimeAtActivity, 'HH:mm').hour() * 60 + moment(this.inputs.maxTimeAtActivity, 'HH:mm').minutes(),
          "isOpenedInDay": []
        },
          "lessons": [],
          "timeDistanceInMinutes": [],
      }
      body["lessons"] = new Array();
      body["timeDistanceInMinutes"] = new Array();
  for(var i = 0; i <  parseInt(this.inputs.numOfWeeks * 7); ++i) {
    body["optimizedActivity"]["isOpenedInDay"].push(true);
  }

  for(let lesson of this.lessons) {
    var lessonToParse = {
      "id": lesson.Id,
      "name": lesson.Name,
      "repeatingPeriod": lesson.repeat_every,
      "possibleTerms": []
    };
    lesson["possibleTerms"] = new Array();
    for(let lsn of this.lessons) {
      console.log(lsn.start_from_week);
      if(parseInt(lsn.Id) == parseInt(lesson.Id)) {
        var term = {
          "startTime": lsn.start,
          "durationInMinutes": moment.duration(moment(lsn.end, 'HH:mm').diff(moment(lsn.start, 'HH:mm'))).asMinutes(),
          "cycleDayNumber": this.getDayIndex(lsn.Day) * 7 + lsn.start_from_week,
        }
        lessonToParse.possibleTerms.push(term);
      }
    }
    body["lessons"].push(new Object(JSON.parse(JSON.stringify(lessonToParse))));
  }
  for(var i = 0; i < this.lessons.length; ++i) {
    var arr = [];
     for(var i = 0; i < this.lessons.length; ++i) {
       arr.push(0);
     }
     body["timeDistanceInMinutes"].push(arr);
  }
    var res = service.post("/api/plans", body);
    }
  }
}
</script>
<style lang="css">

</style>