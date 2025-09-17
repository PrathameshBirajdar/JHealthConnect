@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    // getters and setters
}