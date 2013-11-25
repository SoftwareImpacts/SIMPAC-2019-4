package rina.ipcmanager.impl.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.irati.librina.IPCEvent;

import rina.configuration.RINAConfiguration;
import rina.ipcmanager.impl.IPCManager;

/**
 * Exports a text console to interact with the IPC Manager, reachable from port PORT. It doesn't
 * support multiple clients at the same time.
 * @author eduardgrasa
 *
 */
public class IPCManagerConsole implements Runnable{

	private static final Log log = LogFactory.getLog(IPCManagerConsole.class);
	
	private static final String PROMPT = "ipcmanager> ";
	private static int DEFAULT_PORT = 32766;
	
	private ServerSocket serverSocket = null;
	
	private Map<String, ConsoleCommand> commands = null;
	
	private ReentrantLock lock = null;
	
	private long pendingRequestId = -1;
	
	private BlockingQueue<IPCEvent> responsesQueue = null;
	
	private Timer timer = null;
	
	public IPCManagerConsole(IPCManager ipcManager){
		commands = new Hashtable<String, ConsoleCommand>();
		commands.put(GetSystemCapabilitiesCommand.ID, new GetSystemCapabilitiesCommand(ipcManager, this));
		commands.put(PrintConfigurationCommand.ID, new PrintConfigurationCommand(ipcManager, this));
		commands.put(ListIPCProcessesCommand.ID, new ListIPCProcessesCommand(ipcManager, this));
		commands.put(CreateIPCProcessCommand.ID, new CreateIPCProcessCommand(ipcManager, this));
		commands.put(DestroyIPCProcessCommand.ID, new DestroyIPCProcessCommand(ipcManager, this));
		commands.put(AssignToDIFCommand.ID, new AssignToDIFCommand(ipcManager, this));
		commands.put(UpdateDIFConfigurationCommand.ID, new UpdateDIFConfigurationCommand(ipcManager, this));
		commands.put(RegisterIPCProcessToNMinusOneDIF.ID, new RegisterIPCProcessToNMinusOneDIF(ipcManager, this));
		commands.put(UnregisterIPCProcessFromNMinusOneDIF.ID, new UnregisterIPCProcessFromNMinusOneDIF(ipcManager, this));
		commands.put(EnrollToDIFCommand.ID, new EnrollToDIFCommand(ipcManager, this));
		commands.put(QueryIPCProcessRIBCommand.ID, new QueryIPCProcessRIBCommand(ipcManager, this));
		
		lock = new ReentrantLock();
		responsesQueue = new LinkedBlockingQueue<IPCEvent>();
		timer = new Timer();
	}
	
	public void scheduleTask(TimerTask task, long delay) {
		timer.schedule(task, delay);
	}
	
	public void lock() {
		lock.lock();
	}
	
	public void unlock() {
		lock.unlock();
	}

	public void setPendingRequestId(long pendingRequestId) {
		this.pendingRequestId = pendingRequestId;
	}
	
	public void responseArrived(IPCEvent ipcEvent) {
		lock.lock();
		if (ipcEvent.getSequenceNumber() == pendingRequestId) {
			try {
				responsesQueue.put(ipcEvent);
			} catch (InterruptedException e) {
			}
			
			this.pendingRequestId = -1;
		}
		lock.unlock();
	}
	
	public IPCEvent getResponse(){
		try {
			return responsesQueue.take();
		} catch (InterruptedException e) {
		}
		
		return null;
	}

	public void stop(){
		try{
			serverSocket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void run() {
		/** Command typed by the user **/
		String textCommand = null;
		String[] splittedCommand = null;
		ConsoleCommand command = null;
		String answer = null;
		int port = 0;
		
		try{
			port = RINAConfiguration.getInstance().getLocalConfiguration().getConsolePort();
		}catch(Exception ex){
			port = DEFAULT_PORT;
		}
		
		try{
			serverSocket = new ServerSocket(port);
			log.info("Waiting for connections to the IPC Manager console at port "+port);
			while (true){
				Socket socket = serverSocket.accept();
				String address = socket.getInetAddress().getHostAddress();
				String hostname = socket.getInetAddress().getHostName();
				
				if (!address.equals("127.0.0.1") && !hostname.equals("localhost")){
					log.info("Connection attempt from "+address+" blocked");
					socket.close();
					continue;
				}
				
				log.info("Starting a new console session with "+address);
				InputStream inps = socket.getInputStream();
				OutputStream outs = socket.getOutputStream();
				Scanner in = new Scanner(inps);
				PrintWriter out = new PrintWriter(outs, true);

				out.println("Welcome to the IPC Manager console. Type one or more commands.");
				out.print(PROMPT);
				out.flush();

				boolean done = false;
				while (!done && in.hasNextLine()) {
					textCommand = in.nextLine();
					splittedCommand = textCommand.split(" ");

					if (splittedCommand[0].trim().equalsIgnoreCase("exit")) {
						done = true;
					}else if (splittedCommand[0].trim().equalsIgnoreCase("help")){
						Iterator<String> iterator = commands.keySet().iterator();
						int i = 0;
						while (iterator.hasNext()){
							if (i<5){
								out.print(iterator.next() + " ");
								i++;
							}else{
								i=0; 
								out.print("\n");
							}
						}
						out.print("\n");
						out.print(PROMPT);
						out.flush();
					}else{
						command = commands.get(splittedCommand[0]);
						if (command == null){
							answer = "The command "+splittedCommand[0]+" does not exist. Type 'help' to see a list of all the commands";
						}else{
							answer = command.execute(splittedCommand);
						}
						
						out.println(answer);
						out.print(PROMPT);
						out.flush();
					}
				}

				try {
					log.debug("Finishing IPC Manager console session");
					socket.close();
					socket = null;
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}catch(IOException e){
			log.error(e.getMessage());
		}
	}

}
