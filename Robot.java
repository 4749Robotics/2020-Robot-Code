package frc.robot; //This is our package

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; //Here we choose to import a few nifty things
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX; //Griffin thinks this package is pretty neat.
import edu.wpi.first.wpilibj.Joystick; //If we want
import edu.wpi.first.wpilibj.SpeedControllerGroup; //Now this one right here, that's a game changer.
import edu.wpi.first.wpilibj.TimedRobot; // 
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private final WPI_VictorSPX leftMotor1  = new WPI_VictorSPX(21);
  private final WPI_VictorSPX leftMotor2  = new WPI_VictorSPX(23);
  private final WPI_VictorSPX rightMotor1 = new WPI_VictorSPX(22);
  private final WPI_VictorSPX rightMotor2 = new WPI_VictorSPX(24);
  private final  WPI_TalonSRX winchMotor  = new WPI_TalonSRX (12);

  private final Joystick joystick = new Joystick(0); //1 for the stick and 0 for the xbox controller

  private final Double SpeedScaler  = 0.8;
  private final Double winchSpeed   = 0.8;
  private final Double maxSpeedY    = -SpeedScaler; //  needs to be negative 
  private final Double maxSpeedX    = SpeedScaler;  // needs to be positive
  private final Double rampFunction = 1.0;

  private final SpeedControllerGroup  leftMotor = new SpeedControllerGroup( leftMotor1,  leftMotor2); //Grouping motors together
  private final SpeedControllerGroup rightMotor = new SpeedControllerGroup(rightMotor1, rightMotor2); 
  // The RobotDrive needs two "SpeedControllerGroup"s so we have to group the two together.

  private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);

  @Override
  public void teleopPeriodic() {
    var joystickY = Math.pow(joystick.getRawAxis(1), rampFunction) * maxSpeedY;
    var joystickX = Math.pow(joystick.getRawAxis(0), rampFunction) * maxSpeedX;
    var winchTurn = (joystick.getRawAxis(2) * -1)+joystick.getRawAxis(3);//change code to one direction once built
    
    winchMotor.set(winchTurn * winchSpeed);
    robotDrive.arcadeDrive(joystickY, joystickX);
  }

  @Override
  public void autonomousInit(){
    double start    = Timer.getFPGATimestamp();
    double autoTime = 2.0; // How long our autonomous should run
    while (Timer.getFPGATimestamp()-start <= autoTime) { // Run the robot forward for x seconds. Change the double for the duration
        robotDrive.arcadeDrive(0.6,0);
      
    }
    robotDrive.arcadeDrive(0, 0); // stop the robot
  }



}
