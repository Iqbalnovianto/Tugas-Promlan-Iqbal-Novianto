package model;

public class DataLapangan {
    private String nmLapangan;
    private String Hari;
    private String booking;

    // Constructor lengkap
    public DataLapangan(String nmLapangan, String Hari, String booking) {
        this.nmLapangan = nmLapangan;
        this.Hari = Hari;
        this.booking = booking;
    }
    
    // Constructor kosong (diperlukan oleh JavaFX dalam beberapa kasus)
    public DataLapangan() {
    }

    // --- GETTERS & SETTERS (Diperlukan oleh PropertyValueFactory) ---
    
    public String getNmLapangan() {
        return nmLapangan;
    }

    public void setNmLapangan(String nmLapangan) {
        this.nmLapangan = nmLapangan;
    }

    public String getHari() {
        return Hari;
    }

    public void setHari(String Hari) {
        this.Hari = Hari;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }
}