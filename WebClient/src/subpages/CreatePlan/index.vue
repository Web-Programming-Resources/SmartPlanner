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
                      <b-form-input id="numOfWeeks" v-model="inputs.numOfWeeks" size="sm" class="text-right" type='number'></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.maxCommutes}}</th>
                    <td class="text-right">
                      <b-form-input id="maxCommutes" v-model="inputs.maxCommutes" size="sm" class="text-right" type='number'></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.activityStart}}</th>
                    <td class="text-right">
                      <b-form-input id="activityStart" v-model="inputs.activityStart" size="sm" class="text-right" type='time'></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.activityEnd}}</th>
                    <td class="text-right">
                      <b-form-input id="activityEnd" v-model="inputs.activityEnd" size="sm" class="text-right"  type='time'></b-form-input>
                    </td>
                  </tr>  
                    <tr>
                    <th class="text-left font-weight-normal">{{headers.minTimeAtActivity}}</th>
                    <td class="text-right">
                      <b-form-input id="activityEnd" v-model="inputs.minTimeAtActivity" size="sm" class="text-right" type='time'></b-form-input>
                    </td>
                  </tr>      
                    <tr>
                    <th class="text-left font-weight-normal">{{headers.maxTimeAtActivity}}</th>
                    <td class="text-right">
                      <b-form-input id="activityEnd" v-model="inputs.maxTimeAtActivity" size="sm" class="text-right" type='time'></b-form-input>
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
                      <b-form-input id="lessonName" v-model="inputs.lessonName" size="sm" class="text-right"></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonDay}}</th>
                    <td class="text-right">
                      <b-form-select id="lessonDay" v-model="inputs.lessonDay" size="sm" class="text-right" :options="inputs.days"></b-form-select>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.repeatEvery}}</th>
                    <td class="text-right">
                      <b-form-input id="repeatEvery" v-model="inputs.repeatEvery" size="sm" class="text-right" type='number'></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonStartAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonStartAt" v-model="inputs.lessonStartAt" size="sm" class="text-right"  type='time'></b-form-input>
                    </td>
                  </tr>
                  <tr>
                    <th class="text-left font-weight-normal">{{headers.lessonEndsAt}}</th>
                    <td class="text-right">
                      <b-form-input id="lessonEdnsAt" v-model="inputs.lessonEndsAt" size="sm" class="text-right"  type='time'></b-form-input>
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
export default {
  data() {
    return {
      inputs: {
        numOfWeeks: '',
        maxCommutes: '',
        activityStart: '',
        activityEnd: '',
        minTimeAtActivity: '',
        maxTimeAtActivity: '',
        lessonName: '',
        lessonDay: '',
        repeatEvery: '',
        lessonStartAt: '',
        lessonEndsAt: '',
        lessonId: '',
        days: ''
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
        lessonStartAt: '',
        lessonEndsAt: '',
        lessonId: ''
      },
      lessons: [],
      idCount: 0,
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
          this.headers.repeatEvery = "Powtarzaj co",
          this.headers.lessonStartAt = "Początek",
          this.headers.lessonEndsAt = "Koniec",
          this.headers.removeActivity = "Usun lekcje",
          this.headers.lessonId = "ID",
          this.headers.removeButton = "Usun",
          this.inputs.days = ["Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"];
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
          this.headers.repeatEvery = "Repeat every",
          this.headers.lessonStartAt = "Start",
          this.headers.lessonEndsAt = "End",
          this.headers.removeActivity = "Remove lesson",
          this.headers.lessonId = "ID",
          this.headers.removeButton = "Usun"
          this.inputs.days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
        break;
      }
    },
    addActivity() {
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
    }
  }
}
</script>
<style lang="css">

</style>