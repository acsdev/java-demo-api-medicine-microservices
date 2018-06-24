package br.hackthon.account.commons;

import java.math.BigDecimal;

public class GeoPointDTO implements java.io.Serializable {

    private BigDecimal latigude;

    private BigDecimal longitude;

    public GeoPointDTO(BigDecimal latigude, BigDecimal longitude) {
        super();
        this.latigude = latigude;
        this.longitude = longitude;
    }

    public BigDecimal getLatigude() {
        return latigude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof GeoPointDTO)) {
            return false;
        }
        final GeoPointDTO other = (GeoPointDTO) object;
        if (!(latigude == null ? other.latigude == null : latigude.equals(other.latigude))) {
            return false;
        }
        if (!(longitude == null ? other.longitude == null : longitude.equals(other.longitude))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((latigude == null) ? 0 : latigude.hashCode());
        result = PRIME * result + ((longitude == null) ? 0 : longitude.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String str = super.toString();
        str += "[lat:" + latigude + "|lng" + longitude + "]";
        return str;
    }
}