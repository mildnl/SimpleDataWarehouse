package simpledatawarehouse

class Data {

    String datasource
    String campaign
    Date daily
    int clicks
    int impressions

    static constraints = {
        clicks min: 0
    }

}
