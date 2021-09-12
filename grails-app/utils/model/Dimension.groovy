package model

import grails.validation.Validateable

class Dimension implements Validateable {
    String datasource
    String campaign

    static constraints = {
        datasource nullable: false
    }
}
