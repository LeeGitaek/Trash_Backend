package com.lab

import org.springframework.boot.SpringApplication

class Main extends App {
  SpringApplication.run(classOf[AppConfig]);
}
/**
 *
 *
 * step
 * 1. data
 * Post count param
 * Reply comment count param
 * Certificated mark is exist? Param
 * Like count param
 * Language rank score
 * Hashtag rank score
 * Information weight rank score
 * User Direct message has count rank score
 * Mentoring & consult purchase count rank score
 * Text score count rank score
 * Career rank score
 * Reputation param rank score
 *
 * collects data about this key areas.
 *
 * 2. feature formation
 *
 *  - gtleeCluster
 *  - svhin
 *  - graph
 *  - trust&safety
 *
 * 3. mixer
 *  - in network - real graph, t&s          |           |                   |
 *  - embedding space - sim cluster & twhin |  ======>  |     heavy ranker  |  => Mixing => timeline
 *  - social graph - follow graph           |           |                   |
 *
 *
 *
 *
 */