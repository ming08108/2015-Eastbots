package org.usfirst.frc.team4795.robot;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a short sample program demonstrating how to use the basic throttle
 * mode of the new CAN Talon.
 */
public class Robot extends SampleRobot {

	
	
	CANJaguar winchMotor;
	
	CANJaguar leftMotor;
	CANJaguar rightMotor;
	
	//CANJaguar transverseMotor;  //TODO
	
	Joystick leftJoy;
	Joystick rightJoy;
	  
	private ADXL345_I2C_SparkFun m_accel;
	private GyroITG3200 m_gyro;
	  

  public Robot() {
	  
	  //init motors
      leftMotor = new CANJaguar(1); 
      
      
      //transverseMotor = new CANJaguar(2);  //TODO
      rightMotor = new CANJaguar(3); 
      winchMotor = new CANJaguar(4); 
      
      /*
      //home the elevator
      winchMotor.setVoltageMode();
      winchMotor.enableControl();
      while(winchMotor.getForwardLimitOK()){
    	 winchMotor.set(-0.5);
    	 Timer.delay(0.05);
      }
      */
     
      winchMotor.setPositionMode(CANJaguar.kQuadEncoder, 2048, -40, 0, 5);

      winchMotor.configSoftPositionLimits(0, 6);
      
      
      winchMotor.enableControl(0);
      
      SmartDashboard.putNumber("encoderSet", 0);
      
      
      //init joysticks
      leftJoy = new Joystick(0);
      rightJoy = new Joystick(1);
      
      //init gyro
      m_accel = new ADXL345_I2C_SparkFun(I2C.Port.kOnboard, Accelerometer.Range.k16G);
      m_gyro = new GyroITG3200(I2C.Port.kOnboard);
      m_gyro.initialize();
  }

  /**
    * Runs the motor.
    */
  public void operatorControl() {
	while(isOperatorControl()){
		
		/*
		if(leftJoy.getTrigger()){
			transverseMotor.set(-leftJoy.getX());
			leftMotor.set(0);
			rightMotor.set(0);
		}
		else{
			transverseMotor.set(0);
			leftMotor.set(-leftJoy.getY());
			rightMotor.set(rightJoy.getY());
		}
		*/ //TODO
		
		//set drive motors
		
		
		/*
		
		//add controls for the winch motor
		if(leftJoy.getRawButton(4) || leftJoy.getRawButton(6)){
			if(leftJoy.getRawButton(4)){
				winchMotor.set(-0.50);
			}
			if(leftJoy.getRawButton(6)){
				winchMotor.set(0.50);
			}
		}
		else{
			winchMotor.set(0);
		}
		*/
		
		
	    winchMotor.set(SmartDashboard.getNumber("encoderSet", 0));
		
		Timer.delay(0.05);
		
		System.out.println(m_gyro.getRotationX() + "\t" + m_gyro.getRotationY() + "\t" + m_gyro.getRotationZ());
		
		SmartDashboard.putNumber("encoder", winchMotor.getPosition());
		
		

	}
  }
  
  @Override
  public void disabled(){
	  
  }
  
  
  
  
}
