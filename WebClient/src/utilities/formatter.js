import moment from "moment"

export default {
    minutesToHours(val) {
        return moment().startOf('day').add(val, 'minutes').format('hh:mm');
    }
}