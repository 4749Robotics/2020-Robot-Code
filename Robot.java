package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private final WPI_VictorSPX leftMotor = new WPI_VictorSPX(21);
  private final WPI_VictorSPX rightMotor = new WPI_VictorSPX(23);
  private final WPI_TalonSRX winchMotor = new WPI_TalonSRX(13);

  private final Joystick joystick = new Joystick(0);

  private final Double maxSpeed = 0.8;
  private final Double rampFunction = 2.0;

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

  @Override
  public void teleopPeriodic() {
    var joystickY = Math.pow(joystick.getRawAxis(1), rampFunction) * maxSpeed;
    var joystickX = Math.pow(joystick.getRawAxis(0), rampFunction) * maxSpeed;
    
    robotDrive.arcadeDrive(joystickY, joystickX);
  }
}
