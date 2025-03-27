package lab2_mathys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private long empNo;
    private String ename;
    private String efirst;
    private String job;
    private Emp mgr;
    private Date hireDate;
    private int sal;
    private int comm;
    private int tel;
    private Dept department;
}
