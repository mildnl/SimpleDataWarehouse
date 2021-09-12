package model

import grails.validation.Validateable

class Metric implements Validateable {
    Integer clicks
    Integer impressions
}
