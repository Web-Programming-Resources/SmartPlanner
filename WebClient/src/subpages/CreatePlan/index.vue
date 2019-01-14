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
                    <th class="text-left font-weight-normal">{{headers.lessonDay}}</th>
                    <td class="text-right">
                      <b-form-select id="numOfWeeks" v-model="inputs.addLessonDay" size="sm" class="text-right" :options="inputs.days"  :state="notNullState(this.inputs.addLessonDay)"></b-form-select>
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
     <b-card id="matrix" no-body>
      <b-card-header header-tag="header" class="p-0">
        <b-btn block href="#" v-b-toggle.matrix variant="info" class="text-left">{{headers.matrix}}</b-btn>
      </b-card-header>
      <b-collapse id="matrix" visible>
        <b-container class="mt-3 mb-3">
          <b-row>
            <b-col class="bt br bl bb">ID</b-col>
            <b-col class="bt br bl bb">{{headers.work}}</b-col>
            <b-col v-for="lesson in lessonsToParse" :key="lesson.Id" class="bl bt br">{{lesson.Id}}</b-col>
          </b-row>
          <b-row>
            <b-col class="bt br bl bb">{{headers.work}}</b-col>
            <b-col v-for="i in (0, lessonsToParse.length + 1)" :key="i" class="bl bt br">
              <b-form-input :id="toString(10*i)" v-model="inputs.commuteMatrix[0][i-1]" type='number' size="sm" class="text-right"></b-form-input>
            </b-col>
          </b-row>
          <b-row v-for="(lesson, i) in lessonsToParse" :key="lesson.Id">
            <b-col class="bt br bl bb">{{lesson.Id}}</b-col>
            <b-col class="bt br bl bb">
              <b-form-input :id="toString(10*i)" v-model="inputs.commuteMatrix[i+1][0]" type='number' size="sm" class="text-right"></b-form-input>
            </b-col>
            <b-col v-for="(lesson, j) in lessonsToParse" :key="lesson.Id" class="bt br bl bb">
              <b-form-input :id="toString(10*i+j)" v-model="inputs.commuteMatrix[i+1][j+1]" type='number' size="sm" class="text-right"></b-form-input>
            </b-col>
        </b-row>
        </b-container>
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
        x: '',
        commuteMatrix: [[0]],
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
        addLessonDay: '',
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
        work: '',
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
      idCount: 1,
      lessonsToParse: []
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
          this.inputs.addLessonDay = this.inputs.days[0];
          this.headers.validators.notNull = "Pole nie może być puste";
          this.headers.validators.positiveVal = "Wartość musi być > 0";
          this.headers.validators.repeatEvery = "Wartość musi być <= liczby tygodni w cyklu oraz > 0";
          this.headers.generateButton = "Generuj";
          this.headers.matrix = "Macierz dojazdów";
          this.headers.work = "Praca";
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
          this.inputs.addLessonDay = this.inputs.days[0];
          this.headers.validators.numOfWeeks = "Value must be > 0";
          this.headers.validators.notNull = "Field cannot be empty";
          this.headers.validators.repeatEvery = "Value must be <= weeks in cycle and > 0";
          this.headers.generateButton = "Generate";
          this.headers.matrix = "Commute Matrix";
          this.headers.work = "Work";
        break;
      }
    },
    addActivity() {
      this.lessons.push({Id: this.idCount, Name: this.inputs.lessonName, Day: this.inputs.lessonDay, repeat_every:  this.inputs.repeatEvery, start_from_week: this.inputs.startFromWeek, start: this.inputs.lessonStartAt, end: this.inputs.lessonEndsAt });
      ++this.idCount;
      var body = { "lessons": []};
      this.prepareLessons(body);
      this.inputs.commuteMatrix = new Array(this.lessonsToParse.length + 1);
      for(var i = 0; i < this.lessonsToParse.length + 1; ++i) {
        this.inputs.commuteMatrix[i] = new Array(this.lessonsToParse.length + 1);
        for(var j = 0; j < this.lessonsToParse.length + 1; ++j) {
          this.inputs.commuteMatrix[i][j] = 0;
        }
      }
    },
    addTerm() {
       for(var i = 0; i < this.lessons.length; ++i) {
         if(this.lessons[i].Id == this.inputs.addLessonId) {
          var newTerm = JSON.parse(JSON.stringify(this.lessons[i]));
          newTerm.start = this.inputs.addLessonStartAt; 
          newTerm.end = this.inputs.addLessonEndsAt;
          newTerm.Day = this.inputs.addLessonDay;
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
      this.idCount = 1;
      for(let lesson of this.lessons) {
        lesson.Id = this.idCount;
        ++this.idCount;
      }

    var body = { "lessons": []};
      this.prepareLessons(body);
      this.inputs.commuteMatrix = new Array(this.lessonsToParse.length + 1);
      for(var i = 0; i < this.lessonsToParse.length + 1; ++i) {
        this.inputs.commuteMatrix[i] = new Array(this.lessonsToParse.length + 1);
        for(var j = 0; j < this.lessonsToParse.length + 1; ++j) {
          this.inputs.commuteMatrix[i][j] = 0;
        }
      }
    },
    getDayIndex(day) {
      for(var i = 0 ; i < 7; ++i) {
        if(this.inputs.days[i] === day) {
          return i;
        }
      }
    },

    prepareLessons(body) {
      this.lessonsToParse = JSON.parse(JSON.stringify(this.lessons));
      for(var j = 0; j < this.lessonsToParse.length; ++j) {
        var lessonToParse = {
          "id": this.lessonsToParse[j].Id,
          "name": this.lessonsToParse[j].Name,
          "repeatingPeriod": parseInt(this.lessonsToParse[j].repeat_every * 7),
          "possibleTerms": []
        };
        lessonToParse["possibleTerms"] = new Array();
        var term = {
          "startTime": this.lessonsToParse[j].start,
          "durationInMinutes": moment.duration(moment(this.lessonsToParse[j].end, 'HH:mm').diff(moment(this.lessonsToParse[j].start, 'HH:mm'))).asMinutes(),
          "cycleDayNumber": this.getDayIndex(this.lessonsToParse[j].Day) + 7 * (parseInt(this.lessonsToParse[j].start_from_week) - 1),
        }
        lessonToParse["possibleTerms"].push(term);
        for(var i = j + 1; i < this.lessonsToParse.length; ++i) {
          if(parseInt(this.lessonsToParse[i].Id) == parseInt(this.lessonsToParse[j].Id)) {
            var term = {
              "startTime": this.lessonsToParse[i].start,
              "durationInMinutes": moment.duration(moment(this.lessonsToParse[i].end, 'HH:mm').diff(moment(this.lessonsToParse[i].start, 'HH:mm'))).asMinutes(),
              "cycleDayNumber": this.getDayIndex(this.lessonsToParse[i].Day) + 7 * (parseInt(this.lessonsToParse[i].start_from_week) - 1),
            }
            lessonToParse["possibleTerms"].push(term);
            this.lessonsToParse.splice(i,1);
            --i;
          }
        }
        body["lessons"].push(new Object(JSON.parse(JSON.stringify(lessonToParse))));
      }
    },

    generatePlan() {
      var body = {
        "name" : this.inputs.planName,
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
  this.prepareLessons(body);
  for(var i = 0; i < this.lessonsToParse.length + 1; ++i) {
    for(var j = 0; j < this.lessonsToParse.length + 1; ++j) {
      this.inputs.commuteMatrix[i][j] = parseInt(this.inputs.commuteMatrix[i][j]);
    }
    body["timeDistanceInMinutes"].push(this.inputs.commuteMatrix[i]);
  }
    console.log(body);
    var res = service.post("/api/plans", body);
    }
  }
}
</script>
<style lang="css">

</style>