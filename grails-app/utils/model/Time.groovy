package model

import grails.validation.Validateable

class Time implements Validateable  {
    Date from
    Date to
}
