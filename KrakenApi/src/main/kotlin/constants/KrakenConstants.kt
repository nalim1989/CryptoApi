package constants

object KrakenConstants {
    const val KRAKEN_API_KEY = "7k3YS66bfG92neV1NrjusbgAMduhz7At2bCpmRZaxLaYvWX5CGMOFpf/"
    const val KRAKEN_PRIVATE_KEY = "l87utiFF2qh/nLY85DXrlyd/hGdqTgxH3Vex1nOxyTvvXVB5suZE8SA9cYDbX0pQl5pNNK6Sa81LUEQre4m62A=="

    val REST_HEADERS:Map<String,String> = mapOf(
        "API-Key" to KRAKEN_API_KEY,
        "Content-Type" to "application/x-www-form-urlencoded; charset utf-8")
}